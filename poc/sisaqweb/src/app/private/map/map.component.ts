import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Tile as TileLayer, Vector as VectorLayer } from 'ol/layer';
import { Vector as VectorSource, OSM, TileWMS } from 'ol/source';
import { Style, Stroke, Fill, Text } from 'ol/style';
import { MapService } from './map.service';
import { IBGEWMS } from '../geo/ibge.wms';
import { Camada } from 'src/app/model/camada';
import { Coordenacao } from 'src/app/model/coordenacao';
import { FullScreen } from 'ol/control';
import { MarcaTerreno } from '../geo/marcar-terreno.controls';
import { Feature, Map as MapOL, View } from 'ol';
import { AuthService } from 'src/app/common/auth.service';
import { INPEWMS } from '../geo/inpe.wms';
import { Terreno } from 'src/app/model/terreno';
import { ConfirmEvent } from '../confirm/model/confirm-event';
import { ConfirmService } from '../confirm/confirm.service';
import Geometry from 'ol/geom/Geometry';
import { Draw, Modify, Select, Snap } from 'ol/interaction';
import GeometryType from 'ol/geom/GeometryType';
import { VectorSourceEvent } from 'ol/source/Vector';
import Polygon from 'ol/geom/Polygon';
import GeometryLayout from 'ol/geom/GeometryLayout';
import { AlertaService } from 'src/app/alerta/alerta.service';
import { catchError, filter, finalize, switchMap, tap } from 'rxjs/operators';
import { DatePipe } from '@angular/common';
import { of } from 'rxjs';
import { Geo } from 'src/app/model/geo';

@Component({
  selector: 'sgm-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
  providers: [MapService, DatePipe]
})
export class MapComponent implements OnInit, AfterViewInit {

  /* contantes */
  private readonly LIMITED_INDEX: number = -1;

  /* O array com a lista de coordenações do ibge. */
  public coordenacoes: Array<Coordenacao>;
  public camadas: Array<Camada>;
  public terrenos: Array<Terreno>;

  private camadasSelecionadasIbge: Array<Camada>;
  private camadasSelecionadasInpe: Array<Camada>;

  /* campos */
  public coordenacao: Coordenacao;

  /* O objeto mapa que é visivel. */
  public map: MapOL;

  /* A camada visivel do open street map. */
  private openStreetMap: TileLayer;

  /* A camada visivel do ibge. */
  private ibgeRasterTile: TileLayer;
  private ibgeTile: TileLayer;

  private inpeTile: TileLayer;

  /* A camada terrenos salvos */
  private terrenosVector: VectorLayer;

  /* A camada para desenhar os terrenos */
  private drawVector: VectorLayer;
  private drawSource: VectorSource;

  /* custom controls */
  private marcaTerreno: MarcaTerreno;

  /* iteractions  */
  private snapInteraction: Snap;
  private drawInteraction: Draw;
  private modifyInteraction: Modify;
  private selectInteraction: Select;

  /* terreno selecionado */
  public terreno: Terreno;

  constructor(
    private readonly mapService: MapService,
    private readonly authService: AuthService,
    private readonly confirmService: ConfirmService,
    private readonly alertaService: AlertaService,
    private readonly datePipe: DatePipe,
  ) {

    this.iniciarVariaveisComponentes();

    this.iniciarLayersMapa();

  }

  private iniciarVariaveisComponentes(): void {
    this.camadasSelecionadasIbge = new Array<Camada>();
    this.camadasSelecionadasInpe = new Array<Camada>();
  }

  private iniciarLayersMapa(): void {

    this.openStreetMap = new TileLayer({
      source: new OSM()
    });

    this.ibgeRasterTile = new TileLayer({
      source: new IBGEWMS(this.authService, { 'LAYERS': 'gn:world', 'TILED': true })
    });

    this.ibgeTile = new TileLayer({
      source: new IBGEWMS(this.authService, { 'TILED': true })
    });

    this.inpeTile = new TileLayer({
      source: new INPEWMS(this.authService, { 'TILED': true })
    });

    let terrenoStyle = new Style({
      fill: new Fill({
        color: 'rgba(255, 255, 255, 0.6)',
      }),
      stroke: new Stroke({
        color: '#319FD3',
        width: 1,
      }),
      text: new Text({
        font: '12px Calibri,sans-serif',
        fill: new Fill({
          color: '#000',
        }),
        stroke: new Stroke({
          color: '#fff',
          width: 3,
        }),
      }),
    });

    this.terrenosVector = new VectorLayer({
      style: function (feature) {
        terrenoStyle.getText().setText(feature.get('dataInclusao'));
        return terrenoStyle;
      },
    });

    let drawStyle = new Style({
      text: new Text({
        font: '12px Calibri,sans-serif',
        overflow: true,
        fill: new Fill({
          color: '#000',
        }),
        stroke: new Stroke({
          color: '#fff',
          width: 3,
        })
      }),
      stroke: new Stroke({
        color: 'rgba(82, 31, 137, 1)',
        width: 1,
      }),
      fill: new Fill({
        color: 'rgba(82, 31, 137, 0.3)',
      }),
    });

    this.drawSource = new VectorSource();
    this.drawVector = new VectorLayer({
      source: this.drawSource,
      style: function (feature) {
        drawStyle.getText().setText(`${feature.get('matricula')} - ${feature.get('dataInclusao')}, ${feature.get('largura')} x ${feature.get('comprimento')}`);
        return drawStyle;
      },
    })

    this.ibgeRasterTile.setVisible(true);
    this.openStreetMap.setVisible(false);
  }

  public ngOnInit() {

    this.findAllCoordenacoes();

    this.findAllTerrenos();

    this.map = new MapOL({
      target: 'map',
      layers: [this.openStreetMap, this.ibgeRasterTile, this.ibgeTile, this.inpeTile, this.terrenosVector, this.drawVector],
      view: new View({
        center: [0, 0],
        zoom: 0
      })
    });

    this.iniciarIteractions();

    this.drawSource.on('addfeature', (event: VectorSourceEvent<Geometry>) => {

      if (this.terreno) {
        let feature = event.feature;
        feature.setProperties({
          matricula: this.terreno.matricula,
          dataInclusao: this.datePipe.transform(this.terreno.dataInclusao, 'dd/MM/yyyy'),
          largura: this.terreno.largura,
          comprimento: this.terreno.comprimento
        });
        this.terreno.coordenadas = (<Polygon>feature.getGeometry()).getOrientedFlatCoordinates();
      }

      this.marcaTerreno.disabledMark();
      this.snapInteraction.setActive(false);
      this.drawInteraction.setActive(false);
      this.selectInteraction.setActive(true);
      this.modifyInteraction.setActive(true);
    });

    this.drawSource.on('changefeature', (event: VectorSourceEvent<Geometry>) => {
      if (this.terreno) {
        let feature = event.feature;
        feature.setProperties({
          matricula: this.terreno.matricula,
          dataInclusao: this.datePipe.transform(this.terreno.dataInclusao, 'dd/MM/yyyy'),
          largura: this.terreno.largura,
          comprimento: this.terreno.comprimento
        });
        this.terreno.coordenadas = (<Polygon>feature.getGeometry()).getOrientedFlatCoordinates();
      }
    });

  }

  private iniciarIteractions() {
    this.snapInteraction = new Snap({
      source: this.drawVector.getSource()
    });

    this.drawInteraction = new Draw({
      source: this.drawVector.getSource(),
      type: GeometryType.POLYGON
    });

    this.selectInteraction = new Select({
      layers: [this.drawVector]
    });

    this.modifyInteraction = new Modify({
      features: this.selectInteraction.getFeatures()
    });

    this.map.addInteraction(this.snapInteraction);
    this.map.addInteraction(this.drawInteraction);
    this.map.addInteraction(this.selectInteraction);
    this.map.addInteraction(this.modifyInteraction);

    this.snapInteraction.setActive(false);
    this.drawInteraction.setActive(false);
    this.selectInteraction.setActive(true);
    this.modifyInteraction.setActive(true);

  }

  public findAllCoordenacoes(): void {
    this.mapService.findAllCoordenacoes().subscribe(coordenacoes => {
      this.coordenacoes = coordenacoes;
    });
  }

  public findAllTerrenos(): void {
    this.mapService.findAllTerrenos().subscribe(terrenos => {
      this.terrenos = terrenos;
    });
  }

  public ngAfterViewInit() {
    this.addControls()
  }

  public compare(obj1: any, obj2: any): boolean {
    return obj1 && obj2 ? obj1.id === obj2.id : obj1 == obj2;
  }

  public changeCoordenacao(coordenacao: Coordenacao): void {

    if (!coordenacao) {
      this.camadas = new Array<Camada>();
      return;
    }

    this.mapService.findAllCamadaAtivoByCoordenacaoId(coordenacao.id).subscribe(
      camadas => this.camadas = camadas
    );
  }

  private addControls(): void {
    let fullScrenn = new FullScreen({ source: 'fullscreen' })
    this.map.addControl(fullScrenn);

    this.marcaTerreno = new MarcaTerreno();
    this.marcaTerreno.addClickListener((clicked) => {
      if (clicked && this.terreno && !this.drawSource.isEmpty()) {
        this.confirmService.emit(
          new ConfirmEvent(
            `Já há uma marcação para este terreno de matricula ${this.terreno.matricula}! deseja efetuar uma nova marcação?`,
            () => {
              this.drawSource.clear();
              this.terreno.coordenadas = undefined;
              this.snapInteraction.setActive(true);
              this.drawInteraction.setActive(true);
              this.selectInteraction.setActive(false);
              this.modifyInteraction.setActive(false);
            },
            () => {
              this.marcaTerreno.disabledMark();
              this.snapInteraction.setActive(false);
              this.drawInteraction.setActive(false);
              this.selectInteraction.setActive(true);
              this.modifyInteraction.setActive(true);
            }
          )
        );
      } else {
        this.snapInteraction.setActive(clicked);
        this.drawInteraction.setActive(clicked);
        this.selectInteraction.setActive(!clicked);
        this.modifyInteraction.setActive(!clicked);
      }

    });
    this.map.addControl(this.marcaTerreno);
  }

  private updateParams(camadas: Array<Camada>, tile: TileLayer): void {

    let layers = undefined;

    if (camadas.length > 0) {

      layers = camadas
        .map(cmd => cmd.nome)
        .reduce((result, actual) => result + ',' + actual);
    }

    let params = {
      'LAYERS': layers,
      'TILED': true
    };

    (<TileWMS>tile.getSource()).updateParams(params);
    (<TileWMS>tile.getSource()).refresh();
  }

  public ativarEDesativar(camada: Camada): void {

    if (camada.coordenacao.instituicao === 'IBGE') {
      let index = this.indexSelecionado(camada);
      this.adicionarCamadaIbge(index, camada);
    }

    if (camada.coordenacao.instituicao === 'INPE') {
      let index = this.indexSelecionado(camada);
      this.adicionarCamadaInpe(index, camada);
    }

  }

  private adicionarCamadaIbge(index: number, camada: Camada): void {
    if (index > this.LIMITED_INDEX) {
      this.camadasSelecionadasIbge.splice(index, 1);
    } else {
      this.camadasSelecionadasIbge.push(camada);
    }

    this.updateParams(this.camadasSelecionadasIbge, this.ibgeTile);
  }

  private adicionarCamadaInpe(index: number, camada: Camada): void {
    if (index > this.LIMITED_INDEX) {
      this.camadasSelecionadasInpe.splice(index, 1);
    } else {
      this.camadasSelecionadasInpe.push(camada);
    }

    this.updateParams(this.camadasSelecionadasInpe, this.inpeTile);
  }

  private indexSelecionado(camada: Camada): number {
    let i = this.LIMITED_INDEX;

    let camadas: Array<Camada> = undefined;

    if (camada.coordenacao.instituicao === 'IBGE') {
      camadas = this.camadasSelecionadasIbge;
    }

    if (camada.coordenacao.instituicao === 'INPE') {
      camadas = this.camadasSelecionadasInpe;
    }

    camadas.forEach((element, index) => {
      if (element.id === camada.id) {
        i = index;
      }
    });

    return i;
  }

  public estaAtivo(camada: Camada): string {
    return this.indexSelecionado(camada) > this.LIMITED_INDEX ? 'active' : '';
  }

  public satelite(): void {
    this.ibgeRasterTile.setVisible(true);
    this.openStreetMap.setVisible(false);
  }

  public mapa(): void {
    this.ibgeRasterTile.setVisible(false);
    this.openStreetMap.setVisible(true);
  }

  public selecionarTerreno(terreno: Terreno): void {
    if (this.terreno && terreno.id === this.terreno.id) {
      if (!this.drawVector.getSource().isEmpty()) {
        this.confirmService.emit(
          new ConfirmEvent(
            `Deseja realmente cancelar a marcação do terrreno de matricula ${terreno.matricula}?`,
            () => {
              this.drawVector.getSource().clear();
              this.terreno = null;
              this.marcaTerreno.disabled(true)
              this.drawInteraction.setActive(false);
              this.snapInteraction.setActive(false);
              this.selectInteraction.setActive(true);
              this.modifyInteraction.setActive(true);
            }
          )
        );
      } else {
        this.terreno = null;
        this.marcaTerreno.disabled(true);
      }

    } else {
      this.drawSource.clear();
      this.terreno = terreno;
      this.marcaTerreno.disabled(false);

      of(this.terreno)
        .pipe(
          filter((ter) => !ter.geo),
          switchMap((ter) => this.mapService.findTerrenoGeoByMatricula(ter.matricula)),
          tap((geo: Geo) => console.log(geo)),
          tap((geo: Geo) => this.terreno.geo = geo),
          finalize(() => {
            console.log('consulta', this.terreno);

            let polygon: Polygon = undefined;
            let feature: Feature = undefined;

            if (this.terreno.geo) {
              polygon = new Polygon(
                this.terreno.geo.coordenadas,
                GeometryLayout.XY,
                [this.terreno.geo.coordenadas.length]
              );

              feature = new Feature(polygon);
              feature.setProperties({
                matricula: this.terreno.geo.matricula,
                dataInclusao: this.datePipe.transform(this.terreno.geo.dataInclusao, 'dd/MM/yyyy'),
                largura: this.terreno.largura,
                comprimento: this.terreno.comprimento
              });
            }

            if (!this.terreno.geo && this.terreno.coordenadas) {

              polygon = new Polygon(
                this.terreno.coordenadas,
                GeometryLayout.XY,
                [this.terreno.coordenadas.length]
              );

              feature = new Feature(polygon);
              feature.setProperties({
                matricula: this.terreno.matricula,
                dataInclusao: this.datePipe.transform(new Date(), 'dd/MM/yyyy'),
                largura: this.terreno.largura,
                comprimento: this.terreno.comprimento
              });
            }

            if (feature) {
              console.log('-feature-', feature);
              this.drawSource.addFeature(feature);
            }

          })

        )
        .subscribe();

    }
  }

  public isTerrenoSelecinado(terreno: Terreno): string {
    return (this.terreno && terreno.id === this.terreno.id) ? 'active' : '';
  }

  public limparCoordenada(terreno: Terreno): void {
    this.confirmService.emit(new ConfirmEvent(
      `Deseja realmente remover a marcação do terreno de matricula: ${terreno.matricula}`,
      () => {
        terreno.coordenadas = undefined;
        this.drawSource.clear();
      }
    ));

  }

  public salvarCoordenada(terreno: Terreno): void {

    let geo: Geo = {
      matricula: terreno.matricula,
      coordenadas: terreno.coordenadas,
      dataInclusao: new Date()
    }

    this.mapService
      .saveTerrenosGeo(geo)
      .subscribe(
        () => this.alertaService.info('Coordenada cadastrada com sucesso!'),
        (err) =>  this.alertaService.error('Erro ao cadastra coordenada.')
      );
  }

}
