package org.modelix.sample.restapimodelql

import University.Schedule.N_Courses
import University.Schedule.N_Lecture
import University.Schedule.N_Room
import University.Schedule.N_Rooms
import com.google.gson.Gson
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.modelix.client.light.LightModelClient
import org.modelix.metamodel.typed
import org.modelix.model.api.INode
import org.modelix.model.area.IAreaChangeList
import org.modelix.model.area.IAreaListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.LinkedHashSet

private val logger: Logger = LoggerFactory.getLogger(Connection::class.java)

class Connection(val session: DefaultWebSocketSession) {
    companion object {
        val lastId = AtomicInteger(0)
    }

    val id = "${lastId.getAndIncrement()}"
}

fun Route.UpdateSocketRoute(lightModelClientWrapper: LightModelClientWrapper) {

    // the list of connections to the update socket
    val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())

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
            is N_Room -> broadcast(Gson().toJson(ChangeNotification(WhatChanged.ROOM, node.typed<N_Room>().toJson())))
            is N_Rooms -> broadcast(Gson().toJson(ChangeNotification(WhatChanged.ROOM_LIST, node.typed<N_Rooms>().rooms.toList().toJson())))
            is N_Lecture -> broadcast(Gson().toJson(ChangeNotification(WhatChanged.LECTURE, node.typed<N_Lecture>().toJson())))
            is N_Courses -> broadcast(Gson().toJson(ChangeNotification(WhatChanged.LECTURE_LIST, node.typed<N_Courses>().lectures.toList().toJson())))
            else -> logger.warn("Could not handle change")
        }
    }


    // add the listener to the light model client
    lightModelClientWrapper.getArea().addListener(object : IAreaListener {
        override fun areaChanged(changes: IAreaChangeList) {
            changes.visitChanges {
                application.launch {
                    // todo: get all nodes that were changed and call handleChange for each
                    //it. ?
                    // todo: actually hand over
                    // handleChange(...)
                }
                true// ???
            }

        }
    })

    webSocket("/updates") {

        // adding a new connection
        val thisConnection = Connection(this)
        logger.debug("Opening new update socket connection. [connection={}]", thisConnection.id)
        connections += thisConnection

        try {
            while (true) {
                for (frame in incoming) {
                    // we ignore inputs from clients
                }
                delay(500)
            }
        } catch (e: ClosedReceiveChannelException) {
            // closing a connection regularly
            org.modelix.sample.restapijsonbulk.models.apis.logger.debug("Closing connection regularly ${closeReason.await()} [connection={}]", thisConnection.id)
        } catch (e: Throwable) {
            // closing a connection on error
            org.modelix.sample.restapijsonbulk.models.apis.logger.warn("Closing connection after error. [connection={}, throwable={}]", thisConnection.id, e, e)
            e.printStackTrace()
        } finally {
            thisConnection.session.close()
            connections -= thisConnection
        }
    }
}
