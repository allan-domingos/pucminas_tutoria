import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { Aquisicao } from 'src/app/model/aquisicao';
import { Ativo } from 'src/app/model/ativo';
import { Local } from 'src/app/model/local';
import { Patrimonio } from 'src/app/model/patrimonio';
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

  public aquisicoes(idAtivo : number): Observable<Array<Solicitacao>> {
    return this.http.get<Array<Solicitacao>>(`${environment.wso2_sisaq_api}/aquisicao/ativo/${idAtivo}`).pipe(take(1));
  }

  public obterAquisicao(id: number): Observable<Aquisicao> {
    return this.http.get(`${environment.wso2_sisaq_api}/aquisicao/${id}`).pipe(take(1));
  }

  public obterAtivo(id: number): Observable<Ativo> {
    return this.http.get(`${environment.wso2_sisca_api}/ativo/${id}`).pipe(take(1));
  }

  public cadastrarPatrimonio(patrimonio: Patrimonio): Observable<any> {
    return this.http.post(`${environment.wso2_sisca_api}/patrimonio`,patrimonio).pipe(take(1));
  }

  public atualizarPatrimonio(patrimonio: Patrimonio): Observable<any> {
    return this.http.put(`${environment.wso2_sisca_api}/patrimonio`,patrimonio).pipe(take(1));
  }

  public litarPatrimonio(idAtivo: number): Observable<Array<Patrimonio>> {
    console.log('idAtivo',idAtivo);
    return this.http.get<Array<Patrimonio>>(`${environment.wso2_sisca_api}/patrimonio/ativo/${idAtivo}`).pipe(take(1));
  }

  public listaLocal(): Observable<Array<Local>> {
    return this.http.get<Array<Local>>(`${environment.wso2_sisca_api}/local`).pipe(take(1));
  }

}
