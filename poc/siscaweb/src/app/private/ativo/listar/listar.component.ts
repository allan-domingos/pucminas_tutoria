import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Aquisicao } from 'src/app/model/aquisicao';
import { Ativo } from 'src/app/model/ativo';
import { Solicitacao } from 'src/app/model/solicitacao';
import { AtivoService } from '../ativo.service';

@Component({
  selector: 'siscaweb-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {

  public ativos: Array<Ativo>;
  public solicitacoes: Array<Solicitacao>;
  public aquisicoes: Array<Aquisicao>;

  public ativo: Ativo;

  public dialogAtivoOpen: boolean;
  public dialogSolicitacaoOpen: boolean;
  public dialogSolicitacoesOpen: boolean;

  public formAtivo: FormGroup;

  public formSolicitacao: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly ativoService: AtivoService,
    private readonly messageService: MessageService,
    private readonly confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.initFormAtivo();
    this.initFormSolicitacao();
    this.initData();
  }

  public initFormAtivo(): void {
    this.formAtivo = this.formBuilder.group({
      nome: this.formBuilder.control({ value: undefined }),
      descricao: this.formBuilder.control({ value: undefined }),
      duravel: this.formBuilder.control({ value: false }),
    })
  }

  public initFormSolicitacao(): void {
    this.formSolicitacao = this.formBuilder.group({
      nome: this.formBuilder.control({ value: undefined }),
      descricao: this.formBuilder.control({ value: undefined }),
      quantidade: this.formBuilder.control({ value: undefined }),
    })
  }

  public initData(): void {
    this.ativoService.listar()
      .pipe(
        tap((ativos) => this.ativos = ativos),
        catchError((erro) => {
          this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: 'Erro ao listar ativos!' });
          return throwError(erro);
        })
      )
      .subscribe();
  }

  public cadastrar(): void {
    this.formAtivo.patchValue({
      nome: undefined,
      descricao: undefined,
      duravel: undefined
    });
    this.dialogAtivoOpen = true;
  }

  public editar(ativo: Ativo): void {
    this.ativo = ativo;
    this.formAtivo.patchValue({
      nome: this.ativo.nome,
      descricao: this.ativo.descricao,
      duravel: this.ativo.duravel
    });
    this.dialogAtivoOpen = true;
  }

  public onSubmitAtivo(): void {
    this.dialogAtivoOpen = false;

    if (this.ativo) {
      this.ativo.nome = this.formAtivo.controls.nome.value;
      this.ativo.descricao = this.formAtivo.controls.descricao.value;
      this.ativo.duravel = this.formAtivo.controls.duravel.value;
      this.ativoService.atualizar(this.ativo)
        .subscribe(
          () => {
            this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: `Ativo atualizado com sucesso!` })
            this.initData();
          },
          () => this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao atualizar ativo!` }),
        );
    } else {
      this.ativoService.salvar({
        nome: this.formAtivo.controls.nome.value,
        descricao: this.formAtivo.controls.descricao.value,
        duravel: this.formAtivo.controls.duravel.value
      })
        .subscribe(
          () => {
            this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: `Ativo cadastrado com sucesso!` })
            this.initData();
          },
          () => this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao cadastrar ativo!` }),
        );
    }
    this.ativo = undefined;
  }

  public cadastraSolicitacao(ativo: Ativo): void {
    this.ativo = ativo;
    this.formSolicitacao.patchValue({
      nome: this.ativo.nome,
      descricao: this.ativo.descricao,
      quantidade: undefined
    });
    this.dialogSolicitacaoOpen = true;
  }

  public onSubmitSolicitacao(): void {
    this.dialogSolicitacaoOpen = false;
    let solicitacao: Solicitacao = {
      nome: this.formSolicitacao.controls.nome.value,
      descricao: this.formSolicitacao.controls.descricao.value,
      quantidade: this.formSolicitacao.controls.quantidade.value,
      idAtivo: this.ativo.id
    };

    this.ativoService.solicitar(solicitacao)
      .subscribe(
        () => {
          this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: `Solicitação enviada com sucesso!` })
          this.initData();
        },
        () => this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao enviar solicitação!` }),
      );
    this.ativo = undefined;
  }

  public remover(ativo: Ativo) {
    this.confirmationService.confirm({
      message: `Deseja realmente remover o ativo ${ativo?.nome} ?`,
      header: 'Atenção',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: 'Ativo removido com sucesso!' });
      }
    });
  }

  public verSolicitacao(ativo: Ativo): void {
    this.ativo = ativo;
    this.dialogSolicitacoesOpen = true;
    this.ativoService.solicitacoes(ativo.id)
      .subscribe(
        (solicitacoes) => {
          console.log(solicitacoes)
          this.solicitacoes = solicitacoes;
          this.dialogSolicitacoesOpen = true;
        },
        () => this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao listar solicitações!` }),
      );
  }

  public hideDialogAtivo(): void {
    this.dialogAtivoOpen = false;
    this.ativo = undefined;
  }

  public hideDialogSolicitacao(): void {
    this.dialogSolicitacaoOpen = false;
    this.ativo = undefined;
  }

  public hideDialogSolicitacoes(): void {
    this.dialogSolicitacoesOpen = false;
    this.solicitacoes = undefined;
    this.ativo = undefined;
  }

  public onRowExpand(event: any): void {
    let ativo: Ativo = event.data;
    this.ativoService.aquisicoes(ativo.id)
      .pipe(
        tap((aquisicoes) => {
          ativo.aquisicoes = aquisicoes
        }),
        catchError((error) => {
          this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao listar aquisições!` });
          return throwError(error)
        })
      )
      .subscribe();
  }

}
