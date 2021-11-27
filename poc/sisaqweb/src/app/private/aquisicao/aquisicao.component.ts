import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { throwError } from 'rxjs';
import { catchError, finalize, tap } from 'rxjs/operators';
import { Aquisicao } from 'src/app/model/aquisicao';
import { Solicitacao } from 'src/app/model/solicitacao';
import { AquisicaoService } from './aquisicao.service';

@Component({
  selector: 'sisaqweb-aquisicao',
  templateUrl: './aquisicao.component.html',
  styleUrls: ['./aquisicao.component.css']
})
export class AquisicaoComponent implements OnInit {

  public solicitacoes: Array<Solicitacao>;

  public solicitacao: Solicitacao;

  public aquisicao: Aquisicao;

  public formAquisicao: FormGroup;

  public dialogAquisicaoOpen: boolean;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly aquisicaoService: AquisicaoService,
    private readonly messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.initFormAquisicao();
    this.initData();
  }

  public initFormAquisicao(): void {
    this.formAquisicao = this.formBuilder.group({
      quantidade: this.formBuilder.control({ value: undefined }),
      cnpj: this.formBuilder.control({ value: undefined }),
      notaFiscal: this.formBuilder.control({ value: undefined }),
      valor: this.formBuilder.control({ value: undefined }),
    });
  }

  public initData(): void {
    this.aquisicaoService.listar()
      .pipe(
        tap((solicitacoes) => this.solicitacoes = solicitacoes),
        catchError((error) => {
          this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao listar solicitações!` });
          return throwError(error);
        })
      )
      .subscribe();
  }

  public cadastrar(solicitacao: Solicitacao): void {
    this.solicitacao = solicitacao;
    this.aquisicao = null;
    this.formAquisicao.patchValue({
      cnpj: undefined,
      valor: undefined,
      quantidade: undefined,
      notaFiscal: undefined
    });
    this.dialogAquisicaoOpen = true;
  }

  public editar(solicitacao: Solicitacao, aquisicao: Aquisicao): void {
    this.aquisicao = aquisicao;
    this.solicitacao = solicitacao;
    this.formAquisicao.patchValue({
      cnpj: aquisicao.cnpj,
      valor: aquisicao.valor,
      quantidade: aquisicao.quantidade,
      notaFiscal: aquisicao.notaFiscal
    });
    this.dialogAquisicaoOpen = true;
  }

  public remover(aquisicao: Aquisicao): void {
    this.aquisicao = aquisicao;
  }

  public onSubmitAquisicao(): void {
    this.dialogAquisicaoOpen = false;

    if (this.aquisicao) {
      this.aquisicao.cnpj = this.formAquisicao.controls.cnpj.value.replaceAll(/\D/g,'');
      this.aquisicao.notaFiscal = this.formAquisicao.controls.notaFiscal.value;
      this.aquisicao.valor = this.formAquisicao.controls.valor.value;
      this.aquisicao.quantidade = this.formAquisicao.controls.quantidade.value;
      this.aquisicaoService.atualizar(this.aquisicao)
      .pipe(
        tap(() => {
          this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: `Aquisição atualizado com sucesso!` });
        this.aquisicoes(this.solicitacao);
      }),
      catchError(error => {
        this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao atualizar aquisição!`})
        return throwError(error);
      }),
      finalize(()=> {
        this.aquisicao = undefined;
        this.solicitacao = undefined;
      }))
      .subscribe();
    } else {
      let aquisicao: Aquisicao = {
        notaFiscal: this.formAquisicao.controls.notaFiscal.value,
        cnpj: this.formAquisicao.controls.cnpj.value.replaceAll(/\D/g,''),
        quantidade: this.formAquisicao.controls.quantidade.value,
        valor: this.formAquisicao.controls.valor.value,
        solicitacao: this.solicitacao
      };

      this.aquisicaoService.cadastrar(aquisicao)
      .pipe(
        tap(() => {
          this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: `Aquisição cadastrada com sucesso!` });
          this.aquisicoes(this.solicitacao);
        }),
        catchError(error => {
          this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao cadastra aquisição!`})
          return throwError(error);
        }),
        finalize(()=> {
          this.aquisicao = undefined;
          this.solicitacao = undefined;
        })
      )
      .subscribe();
    }
  }

  public hideDialogAquisicao(): void {
    this.dialogAquisicaoOpen = false;
    this.aquisicao = undefined;
    this.solicitacao = undefined;
  }

  public onRowExpand(event: any) : void {
    let solicitacao : Solicitacao = event.data;
    this.aquisicoes(solicitacao);
  }

  public aquisicoes(solicitacao: Solicitacao){
    this.aquisicaoService.aquisicoes(solicitacao.id)
    .pipe(
      tap((aquisicoes) => { 
        solicitacao.aquisicoes = aquisicoes
      }),
      catchError((error) => {
        this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao listar aquisições!` });
        return throwError(error)
      })
    )
    .subscribe();
  }

}
