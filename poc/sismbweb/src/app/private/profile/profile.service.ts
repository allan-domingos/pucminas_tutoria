import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { take } from "rxjs/operators";
import { Funcionario } from "src/app/model/funcionario";
import { environment } from "src/environments/environment";

@Injectable()
export class ProfileService {

    constructor(private http : HttpClient) {

    }

    public obterProfile(login : string) : Observable<Funcionario> {
        return this.http.get<Funcionario>(`${environment.wso2_sgm_safim}/funcionario/${login}`).pipe(take(1));
    }

}