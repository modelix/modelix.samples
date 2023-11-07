<template>
  <v-container>
    <v-row align-content="center">
      <h1 v-if="rootNode === undefined">Node {{ id }} does not exist.</h1>
      <template v-else>
        <template v-if="isOfConcept_RoomList(rootNode)">
          <RoomTable :room-list="rootNode"></RoomTable>
        </template>
        <template v-if="isOfConcept_LectureList(rootNode)">
          <LectureTable :lecture-list="rootNode"></LectureTable>
        </template>
      </template>
    </v-row>
  </v-container>
</template>

<script lang="ts" setup>
import { MODEL } from "@/InjectionKeys";
import { findRootNodeById } from "@/modelUtils";
import {
  isOfConcept_RoomList,
  isOfConcept_LectureList,
} from "metamodel-api-ts/build/dist/L_University_Schedule";
import { computed, inject } from "vue";
import RoomTable from "@/components/RoomTable.vue";
import LectureTable from "@/components/LectureTable.vue";

const model = inject(MODEL)!;

const props = defineProps<{
  id: string;
}>();

const rootNode = computed(() => {
  return findRootNodeById(model.value?.rootNodes.asArray() ?? [], props.id);
});
</script>
