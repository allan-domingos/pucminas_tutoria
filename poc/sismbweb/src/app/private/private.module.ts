import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { PrivateComponent } from './private.component';
import { HomeComponent } from './home/home.component';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api'
import * as Bootstrap from 'bootstrap'
import { DropdownModule } from 'primeng/dropdown';

import { ROUTES } from './private.routes';
import { HeaderComponent } from './header/header.component';
import { MapComponent } from './map/map.component';
import { CpfPipe } from './pipe/cpf.pipe';

@NgModule({
  declarations: [
    PrivateComponent, 
    HomeComponent, 
    HeaderComponent,
    MapComponent,
    CpfPipe
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(ROUTES),
    ConfirmDialogModule,
    DropdownModule
  ],
  providers: [ConfirmationService]
})

export class PrivateModule { }
