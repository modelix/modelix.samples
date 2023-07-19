package org.modelix.sample.restapimodelql.models.apis

import University.Schedule.N_Lecture
import University.Schedule.N_Room
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.modelix.metamodel.untypedReference
import org.modelix.model.api.serialize
import org.modelix.sample.restapimodelql.LightModelClientWrapper
import org.modelix.sample.restapimodelql.Paths
import org.modelix.sample.restapimodelql.models.Lecture
import org.modelix.sample.restapimodelql.models.LectureList
import org.modelix.sample.restapimodelql.models.Room
import org.modelix.sample.restapimodelql.models.RoomList
import java.net.URLEncoder
import java.nio.charset.Charset

object RouteHelper {
    @JvmStatic
    public fun urlEncode(input: String): String {
        return URLEncoder.encode(input, Charset.defaultCharset())!!
    }
}

fun Route.ModelQLAPI(lightModelClientWrapper: LightModelClientWrapper) {
    get<Paths.getLectures> {
        val allLectures: List<N_Lecture> = lightModelClientWrapper.getAllLectures()

        val lectureList: LectureList = lightModelClientWrapper.runRead {
            LectureList(lectures = allLectures.map { lectureInstance ->
                Lecture(name = lectureInstance.name,
                        description = lectureInstance.description,
                        lectureRef = RouteHelper.urlEncode(lectureInstance.untypedReference().serialize()),
                        room = RouteHelper.urlEncode(lectureInstance.isInRoom?.untypedReference()?.serialize() ?: "UNSET"),
                        maxParticipants = lectureInstance.maximumCapacity)
            })
        }
        call.respond(lectureList)
    }

    get<Paths.getLecturesLectureRef> {
        try {
            val resolvedLecture: N_Lecture = lightModelClientWrapper.resolveNodeIdToConcept(call.parameters["lectureRef"]!!.decodeURLPart())!! as N_Lecture
            val lecture: Lecture = lightModelClientWrapper.runRead {
                Lecture(name = resolvedLecture.name,
                        maxParticipants = resolvedLecture.maximumCapacity,
                        lectureRef = RouteHelper.urlEncode(resolvedLecture.untypedReference().serialize()),
                        room = RouteHelper.urlEncode(resolvedLecture.isInRoom?.untypedReference()?.serialize() ?: "UNSET"),
                        description = resolvedLecture.description
                )
            }
            call.respond(lecture)
        } catch (e: RuntimeException) {
            call.respond(HttpStatusCode.NotFound, "Cannot load Lecture: " + e.message)
        }
    }

    get<Paths.getRooms> {
        val allRooms: List<N_Room> = lightModelClientWrapper.getAllRooms()

        val roomList: RoomList = lightModelClientWrapper.runRead {
            RoomList(rooms = allRooms.map { roomInstance ->
                Room(name = roomInstance.name,
                        maxPlaces = roomInstance.maximumCapacity,
                        roomRef = RouteHelper.urlEncode(roomInstance.untypedReference().serialize()),
                        // TODO: fix to new MM
                        hasRemoteEquipment = false)
            })
        }
        call.respond(roomList)
    }

    get<Paths.getRoomsRoomID> {
        try {
            val resolvedRoom: N_Room = lightModelClientWrapper.resolveNodeIdToConcept(call.parameters["roomRef"]!!.decodeURLPart())!! as N_Room

            val room: Room = lightModelClientWrapper.runRead {
                Room(name = resolvedRoom.name,
                        roomRef = RouteHelper.urlEncode(resolvedRoom.untypedReference().serialize()),
                        maxPlaces = resolvedRoom.maximumCapacity,
                        // TODO: fix to new MM
                        hasRemoteEquipment = true
                )
            }
            call.respond(room)
        } catch (e: RuntimeException) {
            call.respond(HttpStatusCode.NotFound, "Can not load Room: " + e.message)
        }
    }
}
