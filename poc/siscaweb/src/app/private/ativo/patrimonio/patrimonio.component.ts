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

import { Feature, Map, View } from 'ol';
import { Tile } from 'ol/layer';
import { OSM } from 'ol/source';
import { Local } from 'src/app/model/local';
import { Icon, Style } from 'ol/style';
import VectorLayer from 'ol/layer/Vector';
import VectorSource from 'ol/source/Vector';
import Point from 'ol/geom/Point';

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
  public tile: Tile;
  public view: View;
  public style: Style;
  public vectorIcon: VectorLayer;
  public source: VectorSource;

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

    this.source = new VectorSource();

    this.vectorIcon = new VectorLayer({
      style: function (feature) {
        return feature.get('style');
      },
      source: this.source,
    });

    this.style = new Style({
      image: new Icon({
        src: './assets/geo-alt-fill.svg',
        offset: [0, 0],
        opacity: 1,
        scale: 2,
      }),
    });

    this.tile = new Tile({
      source: new OSM()
    });

    this.view = new View({
      projection: 'EPSG:4326',
      center: [0, 0],
      zoom: 0
    })

    this.ativoService.listaLocal()
      .pipe(
        tap((locais: Array<Local>) => {
          let itens: Array<SelectItem> = [{ label: 'Selecione', value: null, }];
          locais.forEach((local) => itens.push({ label: local.nome, value: local }));
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
      layers: [this.tile, this.vectorIcon],
      view: this.view
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
          this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao listar patrim??nios!` });
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
          () => {
            this.initData();
            this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: `Patrim??nio atualizado com sucesso!` });
          },
          () => this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao atualizar patrim??nio!` }),
          () => this.patrimonio = undefined
        );
    } else {
      let patrimonio: Patrimonio = {
        numero: this.formPatrimonio.controls.numero.value,
        ativo: this.ativo
      }
      this.ativoService.cadastrarPatrimonio(patrimonio)
        .subscribe(
          () => {
            this.initData();
            this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: `Patrim??nio cadastrodo com sucesso!` });
          },
          () => this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao cadastrar patrim??nio!` }),
          () => this.patrimonio = undefined
        );
    }
  }

  public remover(patrimonio: Patrimonio): void {
    this.confirmationService.confirm({
      message: `Deseja realmente remover o patrim??nio ${patrimonio?.numero} ?`,
      header: 'Aten????o',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: 'Patrim??nio removido com sucesso!' });
      }
    });
  }

  public alocar(patrimonio: Patrimonio): void {
    this.patrimonio = patrimonio;
    this.dialogAlocacaoOpen = true;
  }

  public hideDialogAlocacao(): void {
    this.dialogAlocacaoOpen = false;
    this.local = undefined;
    this.patrimonio = undefined;
  }

  public salvarAlocacao(): void {
    this.dialogAlocacaoOpen = false;
    let alocacao: Alocacao = {
      local: this.local,
      patrimonio: this.patrimonio,
      ativo: this.ativo,
      idAquisicao: this.aquisicao.id
    };
    this.ativoService.cadastrarAlocacao(alocacao)
      .subscribe(
        () => {
          this.initData();
          this.messageService.add({ key: 'tc', severity: 'success', summary: 'Success', detail: 'Aloca????o cadastrada com sucesso!' })
        },
        () => this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao cadastrar aloca????o!` })
      );

  }

  public onChange(event: any): void {
    let local: Local = event.value;
    this.source.clear();
    this.local = local;

    if (local) {

      let feature = new Feature({
        geometry: new Point(local.coordenadas)
      });
      feature.set('style', this.style);

      this.source.addFeature(feature);

      this.flyTo(local.coordenadas, function () { });
    }
  }

  private flyTo(location, done) {
    const duration = 2000;
    const zoom = this.view.getZoom();
    let parts = 2;
    let called = false;
    function callback(complete) {
      --parts;
      if (called) {
        return;
      }
      if (parts === 0 || !complete) {
        called = true;
        done(complete);
      }
    }
    this.view.animate(
      {
        center: location,
        duration: duration,
      },
      callback
    );
    this.view.animate(
      {
        zoom: zoom - 1,
        duration: duration / 2,
      },
      {
        zoom: zoom,
        duration: duration / 2,
      },
      callback
    );
  }

}
