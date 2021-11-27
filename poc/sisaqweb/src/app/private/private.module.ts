import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { PrivateComponent } from './private.component';
import { HomeComponent } from './home/home.component';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api'
import * as Bootstrap from 'bootstrap'

import { ROUTES } from './private.routes';
import { HeaderComponent } from './header/header.component';
import { CpfPipe } from './pipe/cpf.pipe';


@NgModule({
  declarations: [
    PrivateComponent,
    HomeComponent,
    HeaderComponent,
    CpfPipe
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(ROUTES),
    ConfirmDialogModule
  ],
  providers: [ConfirmationService]
})

export class PrivateModule { }
