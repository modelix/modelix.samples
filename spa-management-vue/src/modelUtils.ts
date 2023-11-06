import { LanguageRegistry } from "@modelix/ts-model-api";
import { ITypedNode, IConceptJS } from "@modelix/ts-model-api";
import { N_BaseConcept } from "metamodel-api-ts/build/dist/L_jetbrains_mps_lang_core";
import { N_Model } from "metamodel-api-ts/build/dist/L_org_modelix_model_repositoryconcepts";
import {
  isOfConcept_LectureList,
  isOfConcept_RoomList,
  isOfConcept_TutorList,
  N_Lecture,
  N_LectureList,
  N_Room,
  N_RoomList,
  N_TutorList,
} from "metamodel-api-ts/build/dist/L_University_Schedule";

export function filterForRoomLists(model: N_Model): N_RoomList[] {
  return model.rootNodes.asArray().filter(isOfConcept_RoomList);
}

export function filterForLectureLists(model: N_Model): N_LectureList[] {
  return model.rootNodes.asArray().filter(isOfConcept_LectureList);
}

export function filterForTutorLists(model: N_Model): N_TutorList[] {
  return model.rootNodes.asArray().filter(isOfConcept_TutorList);
}

export function getNodeId(node: ITypedNode): string {
  return node.unwrap().getReference();
}

export function getDescriptiveNodeIdPart(node: ITypedNode): string {
  // Assume this the form `pnode:3400000019@courses` for node IDs.
  return getNodeId(node).split("@")[0].split(":")[1];
}

export function deleteNode(node: ITypedNode) {
  node.unwrap().getParent()?.removeChild(node.unwrap());
}

export function addNode(node: ITypedNode) {
  node.unwrap().getParent()?.removeChild(node.unwrap());
}

export function findRootNodeById(
  nodes: N_BaseConcept[],
  nodeId: string,
): N_BaseConcept | undefined {
  return nodes.find((node) => getNodeId(node) === nodeId);
}

export function resolveRoom(lecture: N_Lecture): N_Room | undefined {
  const reference = lecture.unwrap().getReferenceTargetRef("isInRoom");
  if (typeof reference === "string" && reference.startsWith("mps")) {
    // References that look like mps-node:r:ce161c54-ea76-... are invalid and caused by a missing feature.
    // see. https://issues.modelix.org/issue/MODELIX-234
    return undefined;
  }
  return lecture.isInRoom;
}

export function setSingleChild<T extends ITypedNode>(node: ITypedNode, role: string, concept: IConceptJS): T {
  const untypedNode = node.unwrap()
  const existingChildren = untypedNode.getChildren(role)
  for (const existingChild of existingChildren) {
    untypedNode.removeChild(existingChild)
  }
  const child = untypedNode.addNewChild(role, -1, concept)
  return LanguageRegistry.INSTANCE.wrapNode(child) as T;
}
