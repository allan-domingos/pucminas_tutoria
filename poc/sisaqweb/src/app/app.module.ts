import { ApplicationRef, DoBootstrap, ErrorHandler, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { OAuthModule } from 'angular-oauth2-oidc';

import { AppComponent } from './app.component';

import { ROUTES } from './app.routes';
import { OAUTH } from './app.oauth';

import { AuthService } from './common/auth.service';
import { LoginInterceptor } from './common/login.interceptor';
import { PrivateGuard } from './private/private.guard';
import { AlertaService } from './alerta/alerta.service';
import { OAuthGlobalErrorHandler } from './common/oauth-global-error.handler';
import { AlertaComponent } from './alerta/alerta.component';
import { LoaderComponent } from './loader/loader.component';
import { LoaderService } from './loader/loader.service';

@NgModule({
  declarations: [
    AppComponent,
    AlertaComponent,
    LoaderComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(ROUTES),
    HttpClientModule,
    OAuthModule.forRoot(OAUTH),
    BrowserAnimationsModule
  ],
  providers: [AuthService, PrivateGuard, AlertaService, LoaderService,
    { provide: ErrorHandler, useClass: OAuthGlobalErrorHandler },
    { provide: HTTP_INTERCEPTORS, useClass: LoginInterceptor, multi: true }
  ]
})

export class AppModule implements DoBootstrap {

  constructor(private authService: AuthService) { }

  ngDoBootstrap(appRef: ApplicationRef): void {
    if (!this.authService.isLoggedIn) {
      this.authService.login();
    }
    appRef.bootstrap(AppComponent);
  }

}
