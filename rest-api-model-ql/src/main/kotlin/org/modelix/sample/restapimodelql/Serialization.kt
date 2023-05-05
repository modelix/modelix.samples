package org.modelix.sample.restapimodelql

import University.Schedule.N_Lecture
import University.Schedule.N_Room
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.modelix.metamodel.typedReference
import org.modelix.metamodel.untypedReference
import org.modelix.model.api.serialize
import org.modelix.sample.restapimodelql.models.Lecture
import org.modelix.sample.restapimodelql.models.LectureList
import org.modelix.sample.restapimodelql.models.Room
import org.modelix.sample.restapimodelql.models.RoomList
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("Serialization")

/**
 * A Kotlin extension function to convert a model lecture to its JSON representation enforced by the generated
 * data class [Lecture].
 */
fun N_Lecture.toJson() = Lecture(
    lectureRef = this.typedReference().ref.serialize(),
    name = this.name,
    description = this.description,
    maxParticipants = this.maxParticipants,
    room = this.room.untypedReference().serialize()
)

/**
 * A Kotlin extension function to convert a model room to its JSON representation enforced by the generated
 * data class [Room].
 */
fun N_Room.toJson() = Room(
    roomRef = this.untypedReference().serialize(),
    name = this.name,
    maxPlaces = this.maxPlaces,
    hasRemoteEquipment = this.hasRemoteEquipment
)

fun List<N_Room>.toJson() = RoomList(this.mapNotNull {
    try {
        it.toJson()
    } catch (e: RuntimeException){
        logger.warn("Ignoring Room with invalid content: ${e.message}")
        return@mapNotNull null
    }
})

fun List<N_Lecture>.toJson() = LectureList(this.mapNotNull {
    try {
        it.toJson()
    } catch (e: RuntimeException){
        logger.warn("Ignoring Lecture with invalid content: ${e.message}")
        return@mapNotNull null
    }
})


@Serializable
enum class WhatChanged {
    ROOM,
    ROOM_LIST,
    LECTURE,
    LECTURE_LIST
}
@Serializable
data class Rooom (
    val roomRef: kotlin.String,
    val name: kotlin.String,
    val maxPlaces: kotlin.Int,
    val hasRemoteEquipment: kotlin.Boolean? = false
)

@Serializable
data class ChangeNotificationRoom(
        val whatChanged: WhatChanged,
        val change: Rooom
)

@Serializable
data class ChangeNotification(
        val whatChanged: WhatChanged,
        @Contextual val change:  Any
)

@Serializable
data class ChangeNotification3(
        val whatChanged: WhatChanged
)
