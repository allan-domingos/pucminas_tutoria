
export class ConfirmEvent {

    public message: string;
    public icon: string;
    public title: string;
    public confirmar: Function;
    public cancelar: Function

    constructor(message : string, confirmar : Function, cancelar? : Function ){
        this.message = message;
        this.icon = 'bi-exclamation-triangle-fill' ;
        this.title ='Atenção.';
        this.confirmar = confirmar;
        this.cancelar = cancelar;
    }

}
