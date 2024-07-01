import { Injectable, NgZone } from '@angular/core';
import { org } from '@modelix/model-client';
import { INodeJS, LanguageRegistry } from '@modelix/ts-model-api';
import { registerLanguages } from 'metamodel-api-ts';
import {
  N_Model,
  N_Module,
} from 'metamodel-api-ts/build/dist/L_org_modelix_model_repositoryconcepts';
const { connectClient } = org.modelix.model.client2;

const modelServerURL = 'http://localhost:28101/v2';
const repositoryId = 'courses';
const branchId = 'master';
type BranchJS = org.modelix.model.client2.BranchJS;

@Injectable({
  providedIn: 'root',
})
export class ModelService {
  private model: N_Model | null = null;
  private connectionError: any = null;

  constructor(private ngZone: NgZone) {
    registerLanguages();
    this.connectToClient();
  }

  private connectToClient() {
    // Connect to modelix outside of Angulars change detection,
    // to trigger change detection only if actually something changed in the model.
    this.ngZone.runOutsideAngular(() => {
      connectClient(modelServerURL)
        .then((client) => client.startReplicatedModel(repositoryId, branchId))
        .then((replicatedModel) => {
          const branch = replicatedModel.getBranch();
          branch.addListener((_change) => {
            this.triggerChangeDetection();
          });
          this.extractModel(branch);
          this.triggerChangeDetection();
        })
        .catch((error) => {
          console.error('Failed to connect to server with error: ', error);
          this.connectionError = error;
          this.triggerChangeDetection();
        });
    });
  }

  extractModel(branch: BranchJS) {
    const untypedModuleNode = branch.rootNode.getAllChildren().at(0);
    if (untypedModuleNode === undefined) {
      throw Error("No module found.");
    }
    const module = LanguageRegistry.INSTANCE.wrapNode(
      untypedModuleNode
    ) as N_Module;
    const model = module.models.asArray().at(0);
    if (model === undefined) {
      throw Error("No model found.");
    }
    this.model = model;
  }

  triggerChangeDetection() {
    this.ngZone.run(() => {});
  }

  getModel(): N_Model | null {
    return this.model;
  }

  getConnectionError() {
    return this.connectionError;
  }
}
