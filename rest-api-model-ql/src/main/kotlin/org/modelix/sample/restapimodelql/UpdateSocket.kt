package org.modelix.sample.restapimodelql


import University.Schedule.N_Lecture
import University.Schedule.N_LectureList
import University.Schedule.N_Room
import University.Schedule.N_RoomList
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.launch
import org.modelix.metamodel.typed
import org.modelix.model.api.INode
import org.modelix.model.area.IAreaChangeList
import org.modelix.model.area.IAreaListener
import org.modelix.sample.restapimodelql.models.Lecture
import org.modelix.sample.restapimodelql.models.LectureList
import org.modelix.sample.restapimodelql.models.Room
import org.modelix.sample.restapimodelql.models.RoomList
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

private val logger: Logger = LoggerFactory.getLogger("UpdateSocket")

class Connection(val session: DefaultWebSocketSession) {
    companion object {
        val lastId = AtomicInteger(0)
    }
    val id = "${lastId.getAndIncrement()}"
}

fun Route.UpdateSocketRoute(lightModelClientWrapper: LightModelClientWrapper) {

    // the list of connections to the update socket
    val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())

    // we register our custom deserializers for the various ChangeNotifications
    val deserializer = ChangeNotificationDeserializer()
    deserializer.registerChangeType(WhatChanged.ROOM, Room::class.java)
    deserializer.registerChangeType(WhatChanged.ROOM_LIST, RoomList::class.java)
    deserializer.registerChangeType(WhatChanged.LECTURE, Lecture::class.java)
    deserializer.registerChangeType(WhatChanged.LECTURE_LIST, LectureList::class.java)

    val gson: Gson = GsonBuilder().registerTypeAdapter(ChangeNotification::class.java, deserializer).create()

    suspend fun broadcast(data: String) {
        connections.forEach {
            try {
                it.session.send(data)
            } catch (e: Throwable) {
                logger.debug("Error broadcasting to connection. [connection={}]", it.id)
            }
        }
    }

    suspend fun handleChange(node: INode) {
        when (node.typed()) {
            is N_Room -> broadcast(gson.toJson(ChangeNotification(WhatChanged.ROOM, node.typed<N_Room>().toJson())))
            is N_RoomList -> broadcast(gson.toJson(ChangeNotification(WhatChanged.ROOM_LIST, node.typed<N_RoomList>().rooms.toList().toJson())))
            is N_Lecture -> broadcast(gson.toJson(ChangeNotification(WhatChanged.LECTURE, node.typed<N_Lecture>().toJson())))
            is N_LectureList -> broadcast(gson.toJson(ChangeNotification(WhatChanged.LECTURE_LIST, node.typed<N_LectureList>().lectures.toList().toJson())))
            else -> logger.warn("Could not handle change")
        }
    }

    // add the listener to the light model client
    lightModelClientWrapper.getArea().addListener(object : IAreaListener {
        override fun areaChanged(changes: IAreaChangeList) {
            changes.visitChanges {
                application.launch {
                    // todo: implement once available in API
                    // todo: get all nodes that were changed and call handleChange for each
                    // handleChange(...)
                }
                true// ???
            }

        }
    })

    // the updates websocket is able to send and receive changes to the model
    // whatever is transmitted is expected to be a ChangeNotification
    webSocket("/updates") {

        // adding a new connection when a new client shows up
        val thisConnection = Connection(this)
        logger.debug("Opening new update socket connection. [connection={}]", thisConnection.id)
        connections += thisConnection

        try {
            while (true) {
                when(val frame = incoming.receive()){
                    is Frame.Text -> {
                        // we iterate over all incoming messages and handle the supported change notifications
                        val changeNotification: ChangeNotification = gson.fromJson(frame.readText(), ChangeNotification::class.java)
                        when (changeNotification.whatChanged){
                            WhatChanged.ROOM -> lightModelClientWrapper.updateRoom(changeNotification.change as Room)
                            WhatChanged.ROOM_LIST -> lightModelClientWrapper.updateRooms(changeNotification.change as RoomList)
                            WhatChanged.LECTURE -> lightModelClientWrapper.updateLecture(changeNotification.change as Lecture)
                            WhatChanged.LECTURE_LIST -> lightModelClientWrapper.updateLectures(changeNotification.change as LectureList)
                            else -> logger.debug("Got unknown change, ignoring. [whatChanged={}, change={}]", changeNotification.whatChanged, changeNotification.change)
                        }

                        // once we processed the changes, we broadcast them to all registered clients to keep them in sync
                        broadcast(frame.readText())
                    }
                    else -> logger.debug("Got unknown frame on WS, ignoring. [frame={}]", frame)
                }
                // TODO: investigate if a delay is required
                // delay(500)
            }
        } catch (e: ClosedReceiveChannelException) {
            // closing a connection regularly
            logger.debug("Closing connection regularly {} [connection={}]", closeReason.await(), thisConnection.id)
        } catch (e: Throwable) {
            // closing a connection on error
            logger.warn("Closing connection after error. [connection={}, throwable={}]", thisConnection.id, e, e)
            e.printStackTrace()
        } finally {
            thisConnection.session.close()
            connections -= thisConnection
        }
    }
}
