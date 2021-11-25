import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { AlertaService } from 'src/app/alerta/alerta.service';
import { Ativo } from 'src/app/model/ativo';
import { AtivoService } from '../ativo.service';

@Component({
  selector: 'siscaweb-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css'],
  providers:[ConfirmationService]
})
export class ListarComponent implements OnInit {

  public ativos: Array<Ativo>;

  public ativo: Ativo;

  public dialogOpen: boolean;

  constructor(
    private readonly ativoService : AtivoService,
    private readonly messageService: MessageService,
    private readonly confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    this.ativoService.listar()
    .pipe(
      tap((ativos) => this.ativos = ativos),
      catchError((erro) => {
        this.messageService.add({key: 'tc', severity:'error', summary: 'Error', detail: 'Erro ao listar ativos!'});
        return throwError(erro);
      })
    )
    .subscribe();
  }

  public cadastrar(): void {
    this.dialogOpen = true;
  }

  public editar(ativo: Ativo): void {
    this.dialogOpen = true;
    this.ativo = ativo;
  }

  public salvar(): void {
    this.dialogOpen = false;
    this.messageService.add({key: 'tc', severity:'success', summary: 'Success', detail: `Ativo ${this.ativo?.id ? 'atualizado': 'cadastrado'} com sucesso!` });
  }

  public remover(ativo: Ativo) {
    this.confirmationService.confirm({
      message: `Deseja realmente remover o ativo ${ativo?.nome} ?`,
      header: 'Atenção',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.messageService.add({key: 'tc', severity:'success', summary: 'Success', detail: 'Ativo removido com sucesso!'});
      }
  });
  }

  public hideDialog(): void {
    this.dialogOpen = false;
    this.ativo = undefined;
  }

}
