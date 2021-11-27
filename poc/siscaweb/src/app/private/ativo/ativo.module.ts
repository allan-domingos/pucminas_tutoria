import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListarComponent } from './listar/listar.component';
import { AtivoComponent } from './ativo.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { ROUTES } from './aitvo.routes';

import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';
import {TooltipModule} from 'primeng/tooltip';

@NgModule({
  declarations: [
    ListarComponent,
    AtivoComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(ROUTES),
    TableModule,
    ButtonModule,
    DialogModule,
    TooltipModule
  ]
})
export class AtivoModule { }
