package org.modelix.sample.restapimodelql

import University.Schedule.N_Lecture
import University.Schedule.N_Room
import com.google.gson.*
import org.modelix.metamodel.typedReference
import org.modelix.metamodel.untypedReference
import org.modelix.model.api.serialize
import org.modelix.sample.restapimodelql.models.Lecture
import org.modelix.sample.restapimodelql.models.LectureList
import org.modelix.sample.restapimodelql.models.Room
import org.modelix.sample.restapimodelql.models.RoomList
import org.slf4j.LoggerFactory
import java.lang.reflect.Type
import java.util.EnumMap

private val logger = LoggerFactory.getLogger("Serialization")

/**
 * A Kotlin extension function to convert a model lecture to its JSON representation enforced by the generated
 * data class [Lecture].
 */
fun N_Lecture.toJson() = Lecture(
    lectureRef = this.untypedReference().serialize(),
    name = this.name,
    description = this.description,
    maxParticipants = this.maximumCapacity,
    // todo: fix to new MM
    room = this.isInRoom?.untypedReference()?.serialize() ?: ""
//            room = this.isInRoom != null ?: this.isInRoom.untypedReference().serialize() else ""
)

/**
 * A Kotlin extension function to convert a model room to its JSON representation enforced by the generated
 * data class [Room].
 */
fun N_Room.toJson() = Room(
    roomRef = this.untypedReference().serialize(),
    name = this.name,
    maxPlaces = this.maximumCapacity,
//    hasRemoteEquipment = this.hasRemoteEquipment
    // todo: fix to new MM
    hasRemoteEquipment = false
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

/**
 * A data class to represent the type of change that was sent/received by the websocket
 */
enum class WhatChanged {
    ROOM,
    ROOM_LIST,
    LECTURE,
    LECTURE_LIST
}
/**
 * A data class to represent the change notification sent/received by the update websocket
 */
data class ChangeNotification(
    val whatChanged: WhatChanged,
    val change: Any
)
/**
 * A Gson deserializer which can distinguish between the different WhatChanged types.
 * Will return the deserialized ChangeNotification
 */
class ChangeNotificationDeserializer : JsonDeserializer<ChangeNotification> {

    private val gson = Gson()
    private val changeTypeRegistry: MutableMap<WhatChanged, Class<out Any?>> = EnumMap(WhatChanged::class.java)
    private val changeTypeElementName = "whatChanged"

    fun registerChangeType(changeTypeName: WhatChanged, changeType: Class<out Any?>) {
        changeTypeRegistry[changeTypeName] = changeType
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ChangeNotification {
        val changeNotificationObject = json!!.asJsonObject

        return when (val whatChanged = WhatChanged.valueOf(changeNotificationObject[changeTypeElementName].asString)) {
            WhatChanged.ROOM -> ChangeNotification(whatChanged,  gson.fromJson(changeNotificationObject["change"], Room::class.java))
            WhatChanged.ROOM_LIST -> ChangeNotification(whatChanged, gson.fromJson(changeNotificationObject["change"], RoomList::class.java))
            WhatChanged.LECTURE -> ChangeNotification(whatChanged, gson.fromJson(changeNotificationObject["change"], Lecture::class.java))
            WhatChanged.LECTURE_LIST -> ChangeNotification(whatChanged, gson.fromJson(changeNotificationObject["change"], LectureList::class.java))
        }
    }
}
