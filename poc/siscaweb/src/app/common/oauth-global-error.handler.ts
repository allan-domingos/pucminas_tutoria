import { HttpResponse } from "@angular/common/http";
import { Injectable, NgZone } from "@angular/core";
import { OAuthResourceServerErrorHandler } from "angular-oauth2-oidc";
import { Observable, throwError } from "rxjs";
import { AlertaService } from "../alerta/alerta.service";

@Injectable()
export class OAuthGlobalErrorHandler implements OAuthResourceServerErrorHandler {

    constructor(private zone : NgZone, private alertaService : AlertaService) {}

    public handleError(response: HttpResponse<any> ): Observable<any> {
        
        let err : any = response;        
        
        console.log(err);

        this.zone.run(()=> {
            
            switch(err.status){

                case 401 :{
                    this.alertaService.error("Autorização negada!");
                    break;
                }
            }
        
        });

        return throwError(response);
    }

}