import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListarComponent } from './listar/listar.component';
import { AtivoComponent } from './ativo.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { ROUTES } from './aitvo.routes';

import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { TooltipModule } from 'primeng/tooltip';
import { PatrimonioComponent } from './patrimonio/patrimonio.component';
import { DropdownModule } from 'primeng/dropdown';

@NgModule({
  declarations: [
    ListarComponent,
    AtivoComponent,
    PatrimonioComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(ROUTES),
    TableModule,
    ButtonModule,
    DialogModule,
    TooltipModule,
    DropdownModule
  ]
})
export class AtivoModule { }
