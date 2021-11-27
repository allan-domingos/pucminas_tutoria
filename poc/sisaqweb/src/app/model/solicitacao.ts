import { Aquisicao } from "./aquisicao";

export interface Solicitacao {
    id?: number,
    quantidade?: number,
    nome?: string,
    descricao?: string,
    dataInclusao?: Date,
    idAtivo?: number,
    aquisicoes?: Array<Aquisicao>
}