import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take, tap } from 'rxjs/operators';
import { Ativo } from 'src/app/model/ativo';
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

}
