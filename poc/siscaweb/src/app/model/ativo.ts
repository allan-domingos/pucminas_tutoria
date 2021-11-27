import { Aquisicao } from "./aquisicao";

export interface Ativo {
    id?: number,
    nome?: string,
    descricao?: string,
    dataInclusao?: Date,
    duravel?: boolean,
    aquisicoes?: Array<Aquisicao>
}