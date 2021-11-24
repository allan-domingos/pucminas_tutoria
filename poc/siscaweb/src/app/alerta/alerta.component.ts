import { Component, OnInit } from '@angular/core';
import { trigger, state, style, transition, animate} from '@angular/animations';
import { AlertaService } from './alerta.service';
import { AlertaEvent } from './model/alerta-event'; 

@Component({
  selector: 'sgm-alerta',
  templateUrl: './alerta.component.html',
  styleUrls: ['./alerta.component.css'],
  animations: [
    trigger('alerta',[
      state('hidden',style({
        opacity: 0,
        top: '0px',
      })),
      state('visible',style({
        opacity: 1,
        top: '80px',
      })),
      transition('hidden => visible', animate('500ms 0s ease-in') ),
      transition('visible => hidden', animate('500ms 0s ease-out') )
    ]),
  ]
})

export class AlertaComponent implements OnInit {

  private _hasAlerta : boolean;
  public alerta : AlertaEvent;
 

  constructor(private alertaService : AlertaService ) { }

  ngOnInit(): void {
    this._hasAlerta = false;
    this.alertaService.addListener(alerta => {
      this.alerta = alerta;
      this._hasAlerta = true;
    },
    timer => {
      this._hasAlerta = false;
    });
  }

  public hasAlerta() : boolean {
    return this._hasAlerta;
  }

}
