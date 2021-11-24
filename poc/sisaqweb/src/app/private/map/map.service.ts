import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { take } from "rxjs/operators";
import { Camada } from "src/app/model/camada";
import { environment } from "src/environments/environment";
import { Coordenacao } from "src/app/model/coordenacao";
import { Terreno } from "src/app/model/terreno";
import { Geo } from "src/app/model/geo";

@Injectable()
export class MapService {

    constructor(private http : HttpClient) {
    }

    public findAllTerrenos() : Observable<Array<Terreno>> {
        return this.http.get<Array<Terreno>>(`${environment.wso2_sgm_stur}/terreno`).pipe(take(1));
    }

    public findAllCoordenacoes() : Observable<Array<Coordenacao>> {
        return this.http.get<Array<Coordenacao>>(`${environment.wso2_sgm_mimg}/coordenacao`).pipe(take(1));
    }

    public findAllCamadaAtivoByCoordenacaoId(idCoordenacao : number) : Observable<Array<Camada>> {
        return this.http.get<Array<Camada>>(`${environment.wso2_sgm_mimg}/camada/${idCoordenacao}`).pipe(take(1));
    }

    public findTerrenoGeoByMatricula(matricula: string) : Observable<any> {
        return this.http.get<any>(`${environment.wso2_sgm_mimg}/terreno/${matricula}`).pipe(take(1));
    }

    public saveTerrenosGeo(geo: Geo) : Observable<any> {
        return this.http.post<any>(`${environment.wso2_sgm_mimg}/terreno/save`, geo);
    }

}