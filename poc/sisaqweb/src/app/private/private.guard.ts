import { CanLoad, Route, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate, UrlSegment } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthService } from '../common/auth.service';


@Injectable()
export class PrivateGuard implements CanLoad, CanActivate {

    constructor(private authService : AuthService){}

    public canLoad() : boolean {
        return this.checkAuthentication();     
    }

    public canActivate() : boolean {
        return this.checkAuthentication();
    }

    private checkAuthentication() : boolean {
        if(!this.authService.isLoggedIn) {
            //this.authService.login();
            return false;
        }
        return true;
    }
}