import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { take } from "rxjs/operators";
import { environment } from "src/environments/environment";
import { Barragem } from "src/app/model/barragem";

@Injectable()
export class MapService {

    constructor(private http : HttpClient) {
    }

    public listarBarragem(): Observable<Array<Barragem>> {
        return this.http.get<Array<Barragem>>(`${environment.wso2_sismb_api}/barragem`).pipe(take(1));
    } 

}