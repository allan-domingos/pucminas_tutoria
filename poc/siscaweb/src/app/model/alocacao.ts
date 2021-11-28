import { Ativo } from "./ativo";
import { Local } from "./local";
import { Patrimonio } from "./patrimonio";

export interface Alocacao {
    id?: number,
    dataInclusao?: Date,
    ativo?: Ativo,
    patrimonio?: Patrimonio,
    local?: Local,
    idAquisicao?: number
}