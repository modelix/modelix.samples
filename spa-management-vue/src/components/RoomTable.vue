<template>
  <v-data-table :headers="headers" :items="rooms" item-value="name">
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>Rooms</v-toolbar-title>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-spacer></v-spacer>
        <v-btn color="primary" dark class="mb-2" @click="openCreateDialog()">
          New Room
        </v-btn>
      </v-toolbar>
    </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon size="small" class="me-2" @click="openEditDialog(toRaw(item))">
        mdi-pencil
      </v-icon>
      <v-icon size="small" @click="openDeleteDialog(toRaw(item))">
        mdi-delete
      </v-icon>
    </template>
  </v-data-table>
  <v-dialog v-model="showCreateDialog" max-width="500px">
    <v-card>
      <v-card-title>
        <span class="text-h5">Edit room</span>
      </v-card-title>

      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12" sm="6" md="4">
              <v-text-field
                v-model="createdNodeData.name"
                label="Name"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="4">
              <v-text-field
                v-model="createdNodeData.number"
                label="Room number"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="4">
              <v-text-field
                v-model="createdNodeData.maximumCapacity"
                label="Maximum capacity"
                type="number"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue-darken-1" variant="text" @click="cancelCreateDialog">
          Cancel
        </v-btn>
        <v-btn color="blue-darken-1" variant="text" @click="confirmCreateDialog">
          Add
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-dialog v-model="showEditDialog" max-width="500px">
    <v-card>
      <v-card-title>
        <span class="text-h5">Edit room</span>
      </v-card-title>

      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12" sm="6" md="4">
              <v-text-field
                v-model="editedNodeData.name"
                label="Name"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="4">
              <v-text-field
                v-model="editedNodeData.number"
                label="Room number"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="4">
              <v-text-field
                v-model="editedNodeData.maximumCapacity"
                label="Maximum capacity"
                type="number"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue-darken-1" variant="text" @click="cancelEditDialog">
          Cancel
        </v-btn>
        <v-btn color="blue-darken-1" variant="text" @click="confirmEditDialog">
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
import { C_Room, N_Room, N_RoomList } from "metamodel-api-ts/build/dist/L_University_Schedule";
import { deleteNode } from "@/modelUtils";
import { Ref, computed, ref, toRaw } from "vue";
import { shallowRef } from "vue";

const props = defineProps<{
  roomList: N_RoomList;
}>();

const rooms = computed(() => props.roomList.rooms.asArray());

const headers = [
  {
    title: "Name",
    key: "name",
  },
  { title: "Room number", key: "number" },
  { title: "Maximum capacity", key: "maximumCapacity" },
  { title: "Actions", key: "actions", sortable: false },
];

interface RoomEditData {
  name: string;
  number: string;
  maximumCapacity: number;
}

const showCreateDialog = ref(false);

const emptyEditedNodeData: RoomEditData = {
  name: "",
  number: "",
  maximumCapacity: 0,
}
Object.freeze(emptyEditedNodeData)

const createdNodeData: Ref<RoomEditData> = ref({...emptyEditedNodeData});
  
const openCreateDialog = () => {
  showCreateDialog.value = true;
  createdNodeData.value = {...emptyEditedNodeData}
}

const cancelCreateDialog = () => {
  showCreateDialog.value = false;
};

const confirmCreateDialog = () => {
  const newRoom = props.roomList.rooms.addNew(C_Room)
  newRoom.name = createdNodeData.value.name
  newRoom.number = createdNodeData.value.number
  newRoom.maximumCapacity = createdNodeData.value.maximumCapacity
  showCreateDialog.value = false;
};

const showEditDialog = ref(false);
const editedNode: Ref<N_Room | undefined> = shallowRef(undefined);

// When editing the data of a room in a dialog,
// we do not make changes directly on the room object,
// because the changes would be send to the server directly.
const editedNodeData: Ref<RoomEditData> = ref({...emptyEditedNodeData});

const openEditDialog = (room: N_Room) => {
  editedNode.value = room;
  editedNodeData.value = {
    name: room.name,
    number: room.number,
    maximumCapacity: room.maximumCapacity,
  };
  showEditDialog.value = true;
};

const cancelEditDialog = () => {
  editedNode.value = undefined;
  showEditDialog.value = false;
};

const confirmEditDialog = () => {
  editedNode.value!.name = editedNodeData.value.name;
  editedNode.value!.number = editedNodeData.value.number;
  editedNode.value!.maximumCapacity = editedNodeData.value.maximumCapacity;
  showEditDialog.value = false;
  editedNode.value = undefined;
};

const showDeleteDialog: Ref<boolean> = ref(false);
const nodeToBeDeleted: Ref<N_Room | undefined> = shallowRef(undefined);

const openDeleteDialog = (room: N_Room) => {
  nodeToBeDeleted.value = room;
  showDeleteDialog.value = true;
};

const cancelDeleteDialog = () => {
  showDeleteDialog.value = false;
  nodeToBeDeleted.value = undefined;
};

const confirmDeleteDialog = () => {
  deleteNode(nodeToBeDeleted.value!);
  nodeToBeDeleted.value = undefined;
  showDeleteDialog.value = false;
};
</script>
