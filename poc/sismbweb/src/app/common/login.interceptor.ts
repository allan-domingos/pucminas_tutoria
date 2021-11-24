import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { LoaderService } from '../loader/loader.service';
import { AuthService } from './auth.service';

@Injectable()
export class LoginInterceptor implements HttpInterceptor {

    constructor(
        private authService: AuthService,
        private loaderServie : LoaderService
        ) { }

    public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        if (!this.authService.isLoggedIn) {
            this.authService.login();
        }

        this.loaderServie.show();

        return next.handle(request).pipe(
            
            finalize(() => { 
                this.loaderServie.hiden();
            })
        );
    }

}