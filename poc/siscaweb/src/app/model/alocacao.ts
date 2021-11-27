import { Local } from "protractor/built/driverProviders";
import { Ativo } from "./ativo";
import { Patrimonio } from "./patrimonio";

export interface Alocacao {
    id?: number,
    dataInclusao?: Date,
    ativo?: Ativo,
    patrimonio?: Patrimonio,
    local?: Local,
    idAquisicao?: number
}