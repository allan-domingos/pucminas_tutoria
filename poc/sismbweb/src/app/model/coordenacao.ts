import { Camada } from "./camada";

export class Coordenacao {

    public id: number
    public instituicao: string;
    public sigla: string;
    public nome: string;
    public dataInclucao: Date;
    public camadas : Array<Camada>;

    constructor(){}

}