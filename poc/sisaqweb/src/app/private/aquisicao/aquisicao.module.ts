import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AquisicaoComponent } from './aquisicao.component';
import { ROUTES } from './aquisicao.routes';

import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {TooltipModule} from 'primeng/tooltip';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    AquisicaoComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(ROUTES),
    TableModule,
    ButtonModule,
    DialogModule,
    ConfirmDialogModule,
    TooltipModule
  ]
})
export class AquisicaoModule { }
