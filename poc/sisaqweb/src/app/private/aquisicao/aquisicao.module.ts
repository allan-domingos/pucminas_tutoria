import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AquisicaoComponent } from './aquisicao.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ROUTES } from './aquisicao.routes';

import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { TooltipModule } from 'primeng/tooltip';
import { RouterModule } from '@angular/router';
import { InputMaskModule } from 'primeng/inputmask';



@NgModule({
  declarations: [
    AquisicaoComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(ROUTES),
    TableModule,
    ButtonModule,
    DialogModule,
    ConfirmDialogModule,
    TooltipModule,
    InputMaskModule
  ]
})
export class AquisicaoModule { }
