import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import * as Bootstrap from 'bootstrap'

import { PrivateComponent } from './private.component';
import { HomeComponent } from './home/home.component';

import { ROUTES } from './private.routes';
import { HeaderComponent } from './header/header.component';
import { MapComponent } from './map/map.component';
import { ProfileComponent } from './profile/profile.component';
import { CpfPipe } from './pipe/cpf.pipe';

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';

@NgModule({
  declarations: [
    PrivateComponent,
    HomeComponent,
    HeaderComponent,
    MapComponent,
    ProfileComponent,
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
