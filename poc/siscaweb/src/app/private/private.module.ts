import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { PrivateComponent } from './private.component';
import { HomeComponent } from './home/home.component';

import { ROUTES } from './private.routes';
import { HeaderComponent } from './header/header.component';
import { ConfirmComponent } from './confirm/confirm.component';
import { ConfirmService } from './confirm/confirm.service';
import { MapComponent } from './map/map.component';
import { ProfileComponent } from './profile/profile.component';
import { CpfPipe } from './pipe/cpf.pipe';

import {ToastModule} from 'primeng/toast';
import {MessageService} from 'primeng/api';

@NgModule({
  declarations: [
    PrivateComponent, 
    HomeComponent, 
    HeaderComponent,
    ConfirmComponent,
    MapComponent,
    ProfileComponent, 
    CpfPipe
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(ROUTES),
    ToastModule
  ],
  providers: [ConfirmService,MessageService]
})

export class PrivateModule { }
