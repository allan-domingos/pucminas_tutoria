import { EventEmitter } from '@angular/core';
import { timer } from 'rxjs';
import { switchMap , tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

import { LoaderComponent } from './loader.component';

export class LoaderService {

    private listener : LoaderComponent;

    private _notifier : EventEmitter<{t :number,b : boolean}>;
    private calls : number;

    constructor(){
        this._notifier = new EventEmitter<{t :number,b : boolean}>();
        this.calls = 0;
    }

    public addListener(listener : any){
        this._notifier.pipe(
            switchMap( obj => {
                return timer(obj.t).pipe(
                    tap(x => listener(obj.b)),
                )
            }),
        ).subscribe();
    }

    public hiden() : void {
        if(--this.calls <= 0) {
            this.calls = 0;
            this._notifier.emit({t :environment.loader_time_hiden, b:false});
        }
    }

    public show() : void {  
        if(this.calls++ == 0){
            this._notifier.emit({t :environment.loader_time_show, b:true}); 
        }
    }
  
}