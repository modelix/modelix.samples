import {
  N_Lecture,
  N_Room,
  isOfConcept_LectureList,
  isOfConcept_OnetimeSchedule,
  isOfConcept_RoomList,
  isOfConcept_WeeklyRecurringSchedule,
} from 'metamodel-api-ts/build/dist/L_University_Schedule';
import { N_Model } from 'metamodel-api-ts/build/dist/L_org_modelix_model_repositoryconcepts';
import { interpretDates } from './dateUtils';

// Unfortunately @modelix/ts-model-api does not export
// the `INodeReferenceJS` type correctly.
type INodeReferenceJS = string;

export function getRooms(model: N_Model): N_Room[] {
  const roomLists = model.rootNodes.asArray().filter(isOfConcept_RoomList);
  return roomLists.flatMap((roomList) => roomList.rooms.asArray());
}

export function getRoom(model: N_Model, reference?: INodeReferenceJS): N_Room | null {
  const roomLists = model.rootNodes.asArray().filter(isOfConcept_RoomList);
  for (const roomList of roomLists) {
    for (const room of roomList.rooms.asArray()) {
      if (room.unwrap().getReference() == reference) {
        return room
      }
    }
  }
  return null
}

/**
 * Represents the occurence of an lecture of a specific date and time.
 * Depending on the declared schedule of a lecture a lecuture might zero, one or many times.
 */
export interface LectureOccurence {
  datetime: Date;
  lectureName: string;
  roomName: string;
}

export function getLectureOccurences(
  model: N_Model,
  isInRoomReference?: INodeReferenceJS
): LectureOccurence[] {
  const lectures = getLectures(model, isInRoomReference);
  const lectureOccurences = lectures.flatMap((lecture) =>
    interpreteLectureOccurence(lecture)
  );
  // Sort asscending by time
  lectureOccurences.sort(({ datetime: a }, { datetime: b }) => a > b ? 1 : a < b ? -1 : 0)
  return lectureOccurences;
}

function getLectures(
  model: N_Model,
  isInRoomReference?: INodeReferenceJS
): N_Lecture[] {
  const lectureLists = model.rootNodes
    .asArray()
    .filter(isOfConcept_LectureList);
  const lectures = lectureLists.flatMap((lectureList) =>
    lectureList.lectures.asArray()
  );
  let filteredLectures;
  if (isInRoomReference === undefined) {
    filteredLectures = lectures;
  } else {
    filteredLectures = lectures.filter(
      (lecture) =>
        // lecture.isInRoom?.unwrap().getReference() would be the better solution,
        // but this would trigger in some cases the bug
        // https://issues.modelix.org/issue/MODELIX-234
        lecture.unwrap().getReferenceTargetRef('isInRoom') == isInRoomReference
    );
  }
  return filteredLectures;
}

/**
 * Interperte the schedule on the lecture a return the occurences with a specific date a tiem.
 *
 * @param lecture The lecture as defined by the model
 * @returns The interpreted occurences.
 */
function interpreteLectureOccurence(lecture: N_Lecture): LectureOccurence[] {
  const schedule = lecture.schedule.get();
  if (schedule === undefined) {
    return [];
  }
  let dates;
  if (isOfConcept_OnetimeSchedule(schedule)) {
    const dateString = schedule.date.get()?.date;
    dates = interpretDates(dateString, dateString, schedule.time.get()?.time);
  } else if (isOfConcept_WeeklyRecurringSchedule(schedule)) {
    dates = interpretDates(
      schedule.startDate.get()?.date,
      schedule.endDate.get()?.date,
      schedule.time.get()?.time
    );
  } else {
    console.error('Unexpected schedule encountered.', schedule);
    throw Error('Unexpected value encountered.');
  }
  const occurences = dates.map((date) => ({
    datetime: date,
    lectureName: lecture.name,
    roomName: lecture.isInRoom?.name ?? '',
  }));
  return occurences;
}
