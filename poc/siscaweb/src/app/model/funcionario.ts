import { Pessoa } from "./pessoa";

export class Funcionario {

    public id : number;
    public matricula : string;
    public email : string;
    public dataAdmissao : Date;
    public pessoa : Pessoa;

    constructor() {

    }
}