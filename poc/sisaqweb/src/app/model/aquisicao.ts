import { Solicitacao } from "./solicitacao";

export interface Aquisicao {
    id?: number,
    quantidade?: number,
    cnpj?: string,
    notaFiscal?: string,
    valor?: number,
    dataInclusao?: Date,
    solicitacao?: Solicitacao
}