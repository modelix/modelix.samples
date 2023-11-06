<template>
  <v-data-table :headers="headers" :items="lectures" item-value="name">
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>Lectures</v-toolbar-title>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-spacer></v-spacer>
        <v-btn
          color="primary"
          dark
          class="mb-2"
          @click="openDataDialogForCreating()"
        >
          New Lecture
        </v-btn>
      </v-toolbar>
    </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon
        size="small"
        class="me-2"
        @click="openDataDialogForEditing(toRaw(item))"
      >
        mdi-pencil
      </v-icon>
      <v-icon size="small" @click="openDeleteDialog(toRaw(item))">
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:[`item.room`]="{ item }">
      {{ resolveRoom(toRaw(item))?.name ?? "" }}
    </template>
    <template v-slot:[`item.schedule`]="{ item }">
      {{ getScheduleString(toRaw(item)) }}
    </template>
  </v-data-table>
  <v-dialog v-model="showDataDialog" max-width="500px">
    <v-card>
      <v-card-title>
        <span class="text-h5">Edit lecture</span>
      </v-card-title>

      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12" sm="6" md="12">
              <v-text-field
                v-model="dataDialogData.name"
                label="Name"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="12">
              <v-text-field
                v-model="dataDialogData.description"
                label="Description"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-text-field
                v-model="dataDialogData.maximumCapacity"
                label="Maximum capacity"
                type="number"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-select
                label="Room"
                clearable
                :items="rooms"
                v-model="dataDialogData.isInRoom"
                item-title="name"
                :item-value="(room) => room?.unwrap().getReference()"
                return-object
              ></v-select>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-text-field
                clearable
                v-model="dataDialogData.date"
                label="Date"
                type="date"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-text-field
                clearable
                v-model="dataDialogData.time"
                label="Time"
                type="time"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-checkbox
                v-model="dataDialogData.repeatWeekly"
                label="Repeat weekly"
              ></v-checkbox>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-text-field
                :disabled="!dataDialogData.repeatWeekly"
                clearable
                v-model="dataDialogData.endDate"
                label="End date"
                type="date"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue-darken-1" variant="text" @click="cancelDataDialog">
          Cancel
        </v-btn>
        <v-btn
          v-if="editedNode === undefined"
          color="blue-darken-1"
          variant="text"
          @click="confirmDataDialogForCreating"
        >
          Create
        </v-btn>
        <v-btn
          v-if="editedNode !== undefined"
          color="blue-darken-1"
          variant="text"
          @click="confirmDataDialogForEditing"
        >
          Save
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-dialog v-model="showDeleteDialog" max-width="500px">
    <v-card>
      <v-card-title class="text-h5"
        >Are you sure you want to delete this item?</v-card-title
      >
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue-darken-1" variant="text" @click="cancelDeleteDialog"
          >Cancel</v-btn
        >
        <v-btn color="blue-darken-1" variant="text" @click="confirmDeleteDialog"
          >Delete</v-btn
        >
        <v-spacer></v-spacer>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script lang="ts" setup>
import {
  C_Lecture,
  C_OnetimeSchedule,
  C_WeeklyRecurringSchedule,
  N_Lecture,
  N_LectureList,
  N_OnetimeSchedule,
  N_Room,
  N_WeeklyRecurringSchedule,
  isOfConcept_OnetimeSchedule,
  isOfConcept_WeeklyRecurringSchedule,
} from "metamodel-api-ts/build/dist/L_University_Schedule";
import { deleteNode, resolveRoom, setSingleChild } from "@/modelUtils";
import { Ref, computed, inject, ref, toRaw } from "vue";
import { shallowRef } from "vue";
import { ROOMS } from "@/InjectionKeys";
import { shallowReactive } from "vue";
import {
  C_Date,
  C_Time,
  N_Date,
  N_Time,
} from "metamodel-api-ts/build/dist/L_University_Schedule_Time";
import {
  formatInputDateStringToModelDateString,
  formatInputTimeStringToModelTimeString,
  formatModelDateToInputDateString,
  formatModelTimeToInputTimeString,
} from "@/dateTimeUtils";

const rooms = inject(ROOMS);

const props = defineProps<{
  lectureList: N_LectureList;
}>();

const lectures = computed(() => props.lectureList.lectures.asArray());

const headers = [
  {
    title: "Name",
    key: "name",
  },
  { title: "Description", key: "description" },
  { title: "Maximum capacity", key: "maximumCapacity" },
  { title: "Room", key: "room" },
  { title: "Schedule", key: "schedule" },
  { title: "Actions", key: "actions", sortable: false },
];

interface LectureData {
  name: string;
  description: string;
  maximumCapacity: number;
  isInRoom: N_Room | undefined;
  date: string | undefined;
  time: string | undefined;
  repeatWeekly: boolean;
  endDate: string | undefined;
}

const emptyEditedNodeData: LectureData = {
  name: "",
  description: "",
  maximumCapacity: 0,
  isInRoom: undefined,
  date: undefined,
  time: undefined,
  repeatWeekly: false,
  endDate: undefined,
};

const showDataDialog = ref(false);
const editedNode: Ref<N_Lecture | undefined> = shallowRef(undefined);

// When editing the data of a lecture in a dialog,
// we do not make changes directly on the lecture object,
// because the changes would be send to the server directly.
const dataDialogData: LectureData = shallowReactive({
  ...emptyEditedNodeData,
});

const openDataDialogForCreating = () => {
  showDataDialog.value = true;
  dataDialogData.name = emptyEditedNodeData.name;
  dataDialogData.description = emptyEditedNodeData.description;
  dataDialogData.maximumCapacity = emptyEditedNodeData.maximumCapacity;
  dataDialogData.isInRoom = emptyEditedNodeData.isInRoom;
  dataDialogData.date = emptyEditedNodeData.date;
  dataDialogData.time = emptyEditedNodeData.time;
  dataDialogData.repeatWeekly = emptyEditedNodeData.repeatWeekly;
  dataDialogData.endDate = emptyEditedNodeData.endDate;
};

const openDataDialogForEditing = (lecture: N_Lecture) => {
  showDataDialog.value = true;
  editedNode.value = lecture;
  dataDialogData.name = lecture.name;
  dataDialogData.description = lecture.description;
  dataDialogData.maximumCapacity = lecture.maximumCapacity;
  dataDialogData.isInRoom = resolveRoom(lecture);
  dataDialogData.date = formatModelDateToInputDateString(getDate(lecture));
  dataDialogData.time = formatModelTimeToInputTimeString(
    lecture.schedule.get()?.time.get(),
  );
  dataDialogData.endDate = formatModelDateToInputDateString(
    getEndDate(lecture),
  );
  dataDialogData.repeatWeekly = dataDialogData.endDate !== undefined;
};

const cancelDataDialog = () => {
  showDataDialog.value = false;
  editedNode.value = undefined;
};

const confirmDataDialogForCreating = () => {
  const newLecture = props.lectureList.lectures.addNew(C_Lecture);
  applyDataToLecture(newLecture);
  showDataDialog.value = false;
};

const confirmDataDialogForEditing = () => {
  applyDataToLecture(editedNode.value!);
  showDataDialog.value = false;
  editedNode.value = undefined;
};

function applyDataToLecture(lecture: N_Lecture) {
  lecture.name = dataDialogData.name;
  lecture.description = dataDialogData.description;
  lecture.maximumCapacity = dataDialogData.maximumCapacity;
  lecture.isInRoom = toRaw(dataDialogData.isInRoom);

  if (dataDialogData.repeatWeekly) {
    if (
      dataDialogData.time !== undefined &&
      dataDialogData.date !== undefined &&
      dataDialogData.endDate !== undefined
    ) {
      let schedule = lecture.schedule.get();
      if (schedule === undefined || !isOfConcept_WeeklyRecurringSchedule(schedule)) {
        schedule = setSingleChild<N_WeeklyRecurringSchedule>(
          lecture,
          "schedule",
          C_WeeklyRecurringSchedule,
        );
      }
      const startDate: N_Date = setSingleChild(schedule, "startDate", C_Date);
      startDate.date = formatInputDateStringToModelDateString(
        dataDialogData.date,
      );
      const time: N_Time = setSingleChild(schedule, "time", C_Time);
      time.time = formatInputTimeStringToModelTimeString(dataDialogData.time);
      const endDate: N_Date = setSingleChild(schedule, "endDate", C_Date);
      endDate.date = formatInputDateStringToModelDateString(
        dataDialogData.endDate,
      );
    }
  } else {
    if (
      dataDialogData.time !== undefined &&
      dataDialogData.date !== undefined
    ) {
      let schedule = lecture.schedule.get();
      if (schedule === undefined || !isOfConcept_OnetimeSchedule(schedule)) {
        schedule = setSingleChild<N_OnetimeSchedule>(
          lecture,
          "schedule",
          C_OnetimeSchedule,
        );
      }

      const date: N_Date = setSingleChild(schedule, "date", C_Date);
      date.date = formatInputDateStringToModelDateString(dataDialogData.date);
      const time: N_Time = setSingleChild(schedule, "time", C_Time);
      time.time = formatInputTimeStringToModelTimeString(dataDialogData.time);
    }
  }
}

const showDeleteDialog: Ref<boolean> = ref(false);
const nodeToBeDeleted: Ref<N_Lecture | undefined> = shallowRef(undefined);

function openDeleteDialog(lecture: N_Lecture) {
  showDeleteDialog.value = true;
  nodeToBeDeleted.value = lecture;
}

function cancelDeleteDialog() {
  showDeleteDialog.value = false;
  nodeToBeDeleted.value = undefined;
}

function confirmDeleteDialog() {
  deleteNode(nodeToBeDeleted.value!);
  showDeleteDialog.value = false;
  nodeToBeDeleted.value = undefined;
}

function getScheduleString(lecture: N_Lecture): string {
  const schedule = lecture.schedule.get();
  if (schedule === undefined) {
    return "no schedule";
  }
  if (isOfConcept_OnetimeSchedule(schedule)) {
    return `${schedule.date.get()?.date} ${schedule.time.get()?.time}`;
  }
  if (isOfConcept_WeeklyRecurringSchedule(schedule)) {
    return `${schedule.startDate.get()?.date} ${schedule.time.get()
      ?.time}, weekly until ${schedule.endDate.get()?.date}`;
  }
  console.error(schedule);
  throw `Unexpected schedule ${schedule}`;
}

function getDate(lecture: N_Lecture): N_Date | undefined {
  const schedule = lecture.schedule.get();
  if (schedule === undefined) {
    return undefined;
  }
  if (isOfConcept_OnetimeSchedule(schedule)) {
    return schedule.date.get();
  }
  if (isOfConcept_WeeklyRecurringSchedule(schedule)) {
    return schedule.startDate.get();
  }
}

function getEndDate(lecture: N_Lecture): N_Date | undefined {
  const schedule = lecture.schedule.get();
  if (schedule === undefined) {
    return undefined;
  }
  if (isOfConcept_WeeklyRecurringSchedule(schedule)) {
    return schedule.endDate.get();
  }
}
</script>
