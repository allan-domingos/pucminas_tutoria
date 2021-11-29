import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { take } from "rxjs/operators";
import { environment } from "src/environments/environment";
import { Barragem } from "src/app/model/barragem";
import { Dado } from "src/app/model/dado";

@Injectable()
export class MapService {

    constructor(private http : HttpClient) {
    }

    public listarBarragem(): Observable<Array<Barragem>> {
        return this.http.get<Array<Barragem>>(`${environment.wso2_sismb_api}/barragem`).pipe(take(1));
    } 

    public listarDados(id: number): Observable<Array<Dado>> {
        return this.http.get<Array<Dado>>(`${environment.wso2_sismb_api}/dado/barragem/${id}`);
    } 

}