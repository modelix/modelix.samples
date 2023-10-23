<template>
  <v-app-bar flat>
    <v-app-bar-title>
      <router-link to="/" class="text-decoration-none" style="color: inherit;">
        Course Management Application
      </router-link>
    </v-app-bar-title>
  </v-app-bar>
  <v-navigation-drawer floating permanent>
    <template v-if="model !== null">
      <v-list>
        <v-list-subheader
          class="text-high-emphasis text-uppercase font-weight-black"
          ><div class="d-flex justify-space-between align-center">
            Room lists
            <v-btn
              variant="plain"
              icon="mdi-plus"
              v-on:click="() => model!.rootNodes.addNew(C_RoomList)"
            ></v-btn>
          </div>
        </v-list-subheader>
        <v-list-item
          :to="'/list/' + getNodeId(roomList)"
          v-for="roomList in filterForRoomLists(model)"
          :key="getNodeId(roomList)"
        >
          <template v-slot:title>Room list</template>
          <template v-slot:subtitle>
            {{ getDescriptiveNodeIdPart(roomList) }}</template
          >
          <template v-slot:append>
            <v-btn
              variant="plain"
              icon="mdi-delete"
              v-on:click.prevent="() => deleteNode(roomList)"
            ></v-btn>
          </template>
        </v-list-item>
        <v-list-subheader
          class="text-high-emphasis text-uppercase font-weight-black"
          ><div class="d-flex justify-space-between align-center">
            Lecture lists
            <v-btn
              variant="plain"
              icon="mdi-plus"
              v-on:click="() => model!.rootNodes.addNew(C_LectureList)"
            ></v-btn>
          </div>
        </v-list-subheader>
        <v-list-item
          :to="'/list/' + getNodeId(lectureList)"
          v-for="lectureList in filterForLectureLists(model)"
          :key="getNodeId(lectureList)"
        >
          <template v-slot:title>Lecture list</template>
          <template v-slot:subtitle>
            {{ getDescriptiveNodeIdPart(lectureList) }}</template
          >
          <template v-slot:append>
            <v-btn
              variant="plain"
              icon="mdi-delete"
              v-on:click.prevent="() => deleteNode(lectureList)"
            ></v-btn>
          </template>
        </v-list-item>
      </v-list>
    </template>
  </v-navigation-drawer>
</template>

<script lang="ts" setup>
import { MODEL } from "@/InjectionKeys";
import {
  filterForRoomLists,
  filterForLectureLists,
  getNodeId,
  getDescriptiveNodeIdPart,
  deleteNode,
} from "@/modelUtils";
import { inject } from "vue";
import {
  C_RoomList,
  C_LectureList,
} from "metamodel-api-ts/build/dist/L_University_Schedule";

const model = inject(MODEL)!;
</script>

<style>
.v-list-subheader__text {
  width: 100%;
}
</style>
