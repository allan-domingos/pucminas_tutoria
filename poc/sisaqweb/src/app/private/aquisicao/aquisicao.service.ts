import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { Aquisicao } from 'src/app/model/aquisicao';
import { Solicitacao } from 'src/app/model/solicitacao';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AquisicaoService {

  constructor(
    private readonly http: HttpClient
  ) { }

  public listar(): Observable<Array<Solicitacao>> {
    return this.http.get<Array<Solicitacao>>(`${environment.wso2_sisaq_api}/solicitacao`).pipe(take(1));
  }

  public aquisicoes(id: number) : Observable<Array<Aquisicao>> {
    return this.http.get<Array<Aquisicao>>(`${environment.wso2_sisaq_api}/aquisicao/solicitacao/${id}`).pipe(take(1));
  }

  public cadastrar(aquisicao: Aquisicao) : Observable<any> {
    return this.http.post<any>(`${environment.wso2_sisaq_api}/aquisicao`,aquisicao).pipe(take(1));
  }

  public atualizar(aquisicao: Aquisicao) : Observable<any> {
    return this.http.put<any>(`${environment.wso2_sisaq_api}/aquisicao`,aquisicao).pipe(take(1));
  }
}
