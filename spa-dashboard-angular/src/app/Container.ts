export class URLLibrary {
    public static API_URL_UPDATES = 'ws://127.0.0.1:8090/updates'
    public static API_URL_ROOMS = 'http://127.0.0.1:8090/rooms';
    public static API_URL_LECTURES = 'http://127.0.0.1:8090/lectures';
}
export class Updatable {}

export class Room extends Updatable{
    roomRef!: string
    name!: string
    maxPlaces!: number
    equipment!: string[]
}

export class RoomList {
    rooms!: Room[];
}

export class Lecture {
    lectureRef!: string
    name!: string
    description!: string
    maxParticipants!: number
    requiredEquipment!: string[]
    room!: string
}

export class LectureList {
    lectures!: Lecture[];
}

export enum WhatChanged {
     ROOM = "ROOM",
     ROOM_LIST = "ROOM_LIST",
     LECTURE = "LECTURE",
     LECTURE_LIST = "LECTURE_LIST"
}

export interface ChangeNotification {
    whatChanged: WhatChanged;
    change: Updatable
}