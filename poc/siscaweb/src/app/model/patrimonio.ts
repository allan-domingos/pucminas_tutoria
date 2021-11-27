import { Ativo } from "./ativo";

export interface Patrimonio {
    id?: number,
    numero?: string,
    dataInlcusao?: Date,
    ativo?: Ativo
}