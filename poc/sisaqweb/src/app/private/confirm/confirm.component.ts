import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ConfirmService } from '../confirm/confirm.service'
import { ConfirmEvent } from './model/confirm-event';
import * as Bootstrap from 'bootstrap'

@Component({
  selector: 'sgm-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  @ViewChild('confirmModal') confirmModal: ElementRef;

  public confirm: ConfirmEvent;

  constructor(private confirmService: ConfirmService) { }

  ngOnInit(): void {
    this.confirmService.addListener(confirm => {
      this.confirm = confirm;
      this.abrir();
    });
  }

  public confirmar() {
    this.confirm.confirmar();
    this.fechar();
  }

  public cancelar() {
    if (this.confirm.cancelar) {
      this.confirm.cancelar();
    }
    this.fechar();
  }

  private abrir() {
    if (this.confirmModal) {
      $(this.confirmModal.nativeElement).modal('show');
    }
  }

  private fechar() {
    if (this.confirmModal) {
      $(this.confirmModal.nativeElement).modal('hide');
    }
  }

}
