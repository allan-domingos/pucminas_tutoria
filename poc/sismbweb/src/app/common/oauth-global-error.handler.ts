import { HttpResponse } from "@angular/common/http";
import { Injectable, NgZone } from "@angular/core";
import { OAuthResourceServerErrorHandler } from "angular-oauth2-oidc";
import { MessageService } from "primeng/api";
import { Observable, throwError } from "rxjs";


@Injectable()
export class OAuthGlobalErrorHandler implements OAuthResourceServerErrorHandler {

    constructor(private zone : NgZone, private messageService : MessageService) {}

    public handleError(response: HttpResponse<any> ): Observable<any> {
        
        let err : any = response;        
        
        console.log(err);

        this.zone.run(()=> {
            
            switch(err.status){

                case 401 :{
                    this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Autorização negada!` });
                    break;
                }
            }
        
        });

        return throwError(response);
    }

}