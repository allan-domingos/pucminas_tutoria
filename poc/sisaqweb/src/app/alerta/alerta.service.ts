import { EventEmitter } from "@angular/core";
import { AlertaEvent } from "./model/alerta-event";
import {switchMap, tap} from 'rxjs/operators'
import { timer } from 'rxjs'

export class AlertaService {

    private _notifier :EventEmitter<AlertaEvent>;

    constructor(){
        this._notifier = new EventEmitter<AlertaEvent>();
    }

    public warning(message: string) : void{
        let alerta : AlertaEvent = new AlertaEvent();
        alerta.message = message;
        alerta.type = 'alert-warning';
        alerta.icon = 'bi-exclamation-triangle-fill';
        this._notifier.emit(alerta);
    }
    
    public error(message: string) : void {
        let alerta : AlertaEvent = new AlertaEvent();
        alerta.message = message;
        alerta.type = 'alert-danger';
        alerta.icon = 'bi-x-octagon-fill';
        this._notifier.emit(alerta);
    }

    public errors(erros: Array<any>) : void {
        let alerta : AlertaEvent = new AlertaEvent();
        alerta.erros = erros;
        alerta.type = 'alert-danger';
        alerta.icon = 'bi-x-octagon-fill';
        this._notifier.emit(alerta);
    }
    
    public info(message: string) : void{
        let alerta : AlertaEvent = new AlertaEvent();
        alerta.message = message;
        alerta.type = 'alert-success';
        alerta.icon = 'bi-check-cicle-fill';
        this._notifier.emit(alerta);
    }

    public addListener(listenerIn : any, listenerOut : any) : void {
        this._notifier.pipe(
            tap(listenerIn),
            switchMap(alter => timer(5000))
        ).subscribe(listenerOut);
    }

}