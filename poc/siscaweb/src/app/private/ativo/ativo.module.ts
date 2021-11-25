import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListarComponent } from './listar/listar.component';
import { AtivoComponent } from './ativo.component';
import { RouterModule } from '@angular/router';
import { ROUTES } from './aitvo.routes';

import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';
import {ConfirmDialogModule} from 'primeng/confirmdialog';

@NgModule({
  declarations: [
    ListarComponent,
    AtivoComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(ROUTES),
    TableModule,
    ButtonModule,
    DialogModule,
    ConfirmDialogModule
  ]
})
export class AtivoModule { }
