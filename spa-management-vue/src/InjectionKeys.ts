import { InjectionKey, Ref } from 'vue';
import { N_Model } from 'metamodel-api-ts/build/dist/L_org_modelix_model_repositoryconcepts'
import { N_Room } from 'metamodel-api-ts/build/dist/L_University_Schedule';

export const MODEL: InjectionKey<Readonly<Ref<N_Model | null>>> = Symbol("MODEL")
export const ROOMS: InjectionKey<Readonly<Ref<N_Room[]>>> = Symbol("ROOMS")
export const LOADING_ERROR: InjectionKey<Readonly<Ref<string | null>>> = Symbol("LOADING_ERROR")
