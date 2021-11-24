import { EventEmitter } from "@angular/core";
import { ConfirmEvent } from "./model/confirm-event";

export class ConfirmService {

    private _notifier : EventEmitter<ConfirmEvent>;

    constructor(){
        this._notifier = new EventEmitter<ConfirmEvent>();
    }

    public addListener(listener : any){
        this._notifier.subscribe(listener);
    }

    public emit(confirm : ConfirmEvent) {
        this._notifier.emit(confirm);
    }
}