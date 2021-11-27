import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { throwError } from 'rxjs';
import { catchError, switchMap, tap } from 'rxjs/operators';
import { Alocacao } from 'src/app/model/alocacao';
import { Aquisicao } from 'src/app/model/aquisicao';
import { Ativo } from 'src/app/model/ativo';
import { Patrimonio } from 'src/app/model/patrimonio';
import { Solicitacao } from 'src/app/model/solicitacao';
import { AtivoService } from '../ativo.service';

import { Map, View } from 'ol';
import { Tile } from 'ol/layer';
import { OSM } from 'ol/source';
import { Local } from 'src/app/model/local';

@Component({
  selector: 'siscaweb-patrimonio',
  templateUrl: './patrimonio.component.html',
  styleUrls: ['./patrimonio.component.css']
})
export class PatrimonioComponent implements OnInit {

  public aquisicao: Aquisicao;
  public ativo: Ativo;
  public solicitacao: Solicitacao;
  public patrimonio: Patrimonio;
  public formPatrimonio: FormGroup;
  public dialogPatrimonioOpen: boolean;
  public patrimonios: Array<Patrimonio>;
  public dialogAlocacaoOpen: boolean;
  public alocacao: Alocacao;
  public locais: Array<SelectItem>;
  public local: Local;

  public map: Map;
  public openStreetMap: Tile;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly activoRoute: ActivatedRoute,
    private readonly ativoService: AtivoService,
    private readonly messageService: MessageService,
    private readonly confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.initHeader();
    this.initFormPatrimonio();
  }

  public initDialogMap(): void {

    this.ativoService.listaLocal()
    .pipe(
      tap((locais: Array<Local>) => {
        let itens : Array<SelectItem> = [{label: 'Selecione', value: null,}];
        locais.forEach((local) => itens.push({label: local.nome, value: local}));
        this.locais = itens;
      }), 
      catchError((error) => {
        this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao listar locais!` });
        return throwError(error);
      })
    )
    .subscribe();
    
    this.map = new Map({
      target: 'map',
      layers: [
        new Tile({
          source: new OSM()
        })
      ],
      view: new View({
        center: [0, 0],
        zoom: 0
      })
    });
  }

  private initHeader(): void {
    this.activoRoute.params.pipe(
      switchMap((params: Params) => this.ativoService.obterAquisicao(params['id'])),
      tap((aquisicao) => {
        this.aquisicao = aquisicao;
        this.solicitacao = aquisicao.solicitacao;
      }),
      switchMap((aquisicao) => this.ativoService.obterAtivo(aquisicao.solicitacao.idAtivo)),
      tap((ativo) => {
        this.ativo = ativo;
        this.initData();
      }),
    )
      .subscribe();
  }

  private initData(): void {
    this.ativoService.litarPatrimonio(this.ativo.id)
      .pipe(
        tap((patrimonios) => {
          this.patrimonios = patrimonios
        }),
        catchError((error) => {
          this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao listar patrimônios!` });
          return throwError(error);
        })
      )
      .subscribe();
  }

  public initFormPatrimonio(): void {
    this.formPatrimonio = this.formBuilder.group({
      numero: this.formBuilder.control({ value: undefined }),
    })
  }

  public cadastrar(): void {
    this.formPatrimonio.patchValue({
      numero: undefined
    });
    this.dialogPatrimonioOpen = true;
  }

  public editar(patrimonio: Patrimonio): void {
    this.patrimonio = patrimonio;
    this.formPatrimonio.patchValue({
      numero: patrimonio.numero
    });
    this.dialogPatrimonioOpen = true;
  }

  public hideDialogPatrimonio(): void {
    this.dialogPatrimonioOpen = false;
    this.patrimonio = undefined
  }

  public onSubmitPatrimonio() {
    this.dialogPatrimonioOpen = false;
    if (this.patrimonio) {
      this.patrimonio.numero = this.formPatrimonio.controls.numero.value;
      this.ativoService.atualizarPatrimonio(this.patrimonio)
        .subscribe(
          () => this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: `Patrimônio atualizado com sucesso!` }),
          () => this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao atualizar patrimônio!` }),
          () => this.patrimonio = undefined
        );
    } else {
      let patrimonio: Patrimonio = {
        numero: this.formPatrimonio.controls.numero.value,
        ativo: this.ativo
      }
      this.ativoService.cadastrarPatrimonio(patrimonio)
        .subscribe(
          () => this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: `Patrimônio cadastrodo com sucesso!` }),
          () => this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao cadastrar patrimônio!` }),
          () => this.patrimonio = undefined
        );
    }
  }

  public remover(patrimonio: Patrimonio): void {
    this.confirmationService.confirm({
      message: `Deseja realmente remover o patrimônio ${patrimonio?.numero} ?`,
      header: 'Atenção',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: 'Patrimônio removido com sucesso!' });
      }
    });
  }

  public alocar(patrimonio: Patrimonio): void {
    this.patrimonio = patrimonio;
    this.dialogAlocacaoOpen = true;
  }

  public hideDialogAlocacao(): void {
    this.dialogAlocacaoOpen = false;
  }

  public salvarAlocacao(): void {
    this.dialogAlocacaoOpen = false;
    this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: 'Alocação efetuada com sucesso!' });
  }

  public onChange(event: any): void {
    console.log(event);
  }

}
