import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take, tap } from 'rxjs/operators';
import { Ativo } from 'src/app/model/ativo';
import { Solicitacao } from 'src/app/model/solicitacao';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AtivoService {

  constructor(
    private readonly http: HttpClient
  ) {}

  public listar(): Observable<Array<Ativo>> {
    return this.http.get<Array<Ativo>>(`${environment.wso2_sisca_api}/ativo`).pipe(take(1));
  } 

  public salvar(ativo: Ativo): Observable<any> {
    return this.http.post(`${environment.wso2_sisca_api}/ativo`, ativo).pipe(take(1));
  }

  public atualizar(ativo: Ativo): Observable<any> {
    return this.http.put(`${environment.wso2_sisca_api}/ativo`, ativo).pipe(take(1));
  }

  public deletar(id: number): Observable<any> {
    return this.http.delete(`${environment.wso2_sisca_api}/ativo/${id}`).pipe(take(1));
  }

  public solicitar(solicitacao: Solicitacao): Observable<any> {
    return this.http.post(`${environment.wso2_sisaq_api}/solicitacao`,solicitacao).pipe(take(1));
  }

  public solicitacoes(idAtivo : number): Observable<Array<Solicitacao>> {
    return this.http.get<Array<Solicitacao>>(`${environment.wso2_sisaq_api}/solicitacao/ativo/${idAtivo}`).pipe(take(1));
  }

}
