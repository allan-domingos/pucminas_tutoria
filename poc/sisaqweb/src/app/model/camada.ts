import { Coordenacao } from "./coordenacao";

export class Camada {

    public id: number;
    public nome: string;
    public titulo: string;
    public dataInclucao: Date;
    public ativo : boolean;
    public coordenacao : Coordenacao;

    constructor(){}
    
}