<template>
  <router-view />
</template>

<script lang="ts" setup>
import { LanguageRegistry } from "@modelix/ts-model-api";
import { useModelClient, useRootNode } from "@modelix/vue-model-api";
import { registerLanguages } from "metamodel-api-ts";
import { N_Module } from "metamodel-api-ts/build/dist/L_org_modelix_model_repositoryconcepts";
import { Ref, computed, provide } from "vue";
import { LOADING_ERROR, MODEL, ROOMS } from "@/InjectionKeys";
import { isOfConcept_RoomList } from "metamodel-api-ts/build/dist/L_University_Schedule";

registerLanguages();

const modelServerURL = "http://localhost:28101/v2";
const repositoryId = "courses";
const branchId = "master";
const { client, error: serverError } = useModelClient(modelServerURL);
// The `rootNode` is not the MPS root node,
// but just the top most node in the branch on the model server.
const { rootNode, error: branchError } = useRootNode(
  client,
  repositoryId,
  branchId,
);

const loadingError: Ref<string | null> = computed(() => {
  if (serverError.value !== null) {
    return String(serverError.value);
  }
  if (branchError.value !== null) {
    return String(branchError.value);
  }
  return null;
});

const model = computed(() => {
  if (rootNode.value == null) {
    return null;
  }
  const untypedModuleNode = rootNode.value.getAllChildren().at(0);
  if (untypedModuleNode === undefined) {
    return null;
  }
  const module = LanguageRegistry.INSTANCE.wrapNode(
    untypedModuleNode,
  ) as N_Module;
  const model = module.models.asArray().at(0);
  if (model === undefined) {
    return null;
  }
  return model;
});

const rooms = computed(() => {
  const rootNodes = model.value?.rootNodes.asArray() ?? [];
  const roomLists = rootNodes.filter(isOfConcept_RoomList)
  return roomLists.flatMap(roomList => roomList.rooms.asArray())
})

provide(MODEL, model);
provide(ROOMS, rooms);
provide(LOADING_ERROR, loadingError);
</script>
