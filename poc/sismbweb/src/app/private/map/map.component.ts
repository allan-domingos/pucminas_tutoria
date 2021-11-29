import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Tile as TileLayer, Vector as VectorLayer } from 'ol/layer';
import { Vector as VectorSource, OSM } from 'ol/source';
import { Icon, Style } from 'ol/style';
import { MapService } from './map.service';
import { FullScreen } from 'ol/control';
import { DatePipe } from '@angular/common';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { Feature, Map, Overlay, View } from 'ol';
import { Barragem } from 'src/app/model/barragem';
import { catchError, filter, map, switchMap, tap } from 'rxjs/operators';
import { interval, Observable, of, throwError } from 'rxjs';
import Polygon from 'ol/geom/Polygon';
import GeometryLayout from 'ol/geom/GeometryLayout';
import SimpleGeometry from 'ol/geom/SimpleGeometry';
import { Dado } from 'src/app/model/dado';
import Point from 'ol/geom/Point';
import OverlayPositioning from 'ol/OverlayPositioning';

@Component({
  selector: 'sgm-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
  providers: [MapService, DatePipe]
})
export class MapComponent implements OnInit, AfterViewInit {

  public barragens: Array<SelectItem>;
  public barragem: Barragem;
  public dados: Array<Dado>;

  /* O objeto mapa que é visivel. */
  public map: Map;
  public view: View;
  public icon: Style;
  public overlay: Overlay;

  /* A camada visivel do open street map. */
  private openStreetMap: TileLayer;

  /* A camada para desenhar os terrenos */
  private barragemVector: VectorLayer;
  private barragemSource: VectorSource;

  private sensoresVector: VectorLayer;
  private sensoresSource: VectorSource;

  constructor(
    private readonly mapService: MapService,
    private readonly messageService: MessageService
  ) {
    this.iniciarLayersMapa();
  }

  private iniciarLayersMapa(): void {

    this.openStreetMap = new TileLayer({
      source: new OSM()
    });

    this.barragemSource = new VectorSource();
    this.barragemVector = new VectorLayer({
      source: this.barragemSource
    });

    this.sensoresSource = new VectorSource();
    this.sensoresVector = new VectorLayer({
      style: function (feature) {
        return feature.get('style');
      },
      source: this.sensoresSource
    });
  }

  public ngOnInit() {

    this.initData();

    this.initTelemetria();

    this.icon = new Style({
      image: new Icon({
        src: './assets/geo-alt-fill.svg',
        offset: [0, 0],
        opacity: 1,
        scale: 2,
      }),
    });

    this.view = new View({
      projection: 'EPSG:4326',
      center: [0, 0],
      zoom: 0
    });

    this.map = new Map({
      target: 'map',
      layers: [this.openStreetMap, this.barragemVector, this.sensoresVector],
      view: this.view
    });
  }

  private initTelemetria(): void {
    interval(5000)
      .pipe(
        tap(() => { 
          this.map.getOverlays().clear();
          this.sensoresSource.clear();
        }),
        filter(() => !!this.barragem),
        switchMap(() => this.mapService.listarDados(this.barragem.id)),
        tap((dados) => {
          this.setIconMap(dados)
          this.dados = dados;
        }),
      )
      .subscribe();
  }

  private setIconMap(dados: Array<Dado>) {
    $('.popover').popover('hide');
    dados.forEach((dado) => {
      if (dado.coordenadas && dado.coordenadas.length > 1) {
        let feature = new Feature({
          geometry: new Point(dado.coordenadas)
        });
        feature.set('style', this.icon);

        this.sensoresSource.addFeature(feature);

        let element = document.getElementById(`popup-${dado.id}`);

        let popup = new Overlay({
          element: element,
          positioning: OverlayPositioning.BOTTOM_CENTER,
          stopEvent: false,
        });
        popup.setPosition(dado.coordenadas);

        this.map.addOverlay(popup);
        
        $(element).popover({
          placement: 'top',
          html: true,
          content: dado.nome,
        });
        $(element).popover('show');
      }
    });
  }

  private initData() {
    this.mapService.listarBarragem()
      .pipe(
        tap((barragens) => {
          let itens: Array<SelectItem> = [{ label: 'Selecione', value: null }];
          barragens.forEach((barragem) => itens.push({ label: barragem.nome, value: barragem }));
          this.barragens = itens;
        }),
        catchError((error) => {
          this.messageService.add({ key: 'tc', severity: 'error', summary: 'Error', detail: `Erro ao listar barragens!` })
          return throwError(error);
        })
      )
      .subscribe();
  }

  public ngAfterViewInit() {
    this.addControls()
  }

  private addControls(): void {
    let fullScrenn = new FullScreen({ source: 'fullscreen' })
    this.map.addControl(fullScrenn);
  }

  public onChange(event: any): void {
    console.log(event);
    this.barragem = event.value;
    if (this.barragem) {
      let feature = new Feature({
        geometry: new Polygon(
          this.barragem.coordenadas,
          GeometryLayout.XY,
          [this.barragem.coordenadas.length]
        )
      });
      this.barragemSource.addFeature(feature);
      this.flyTo(this.barragem.coordenadas, () => { this.view.fit(<SimpleGeometry>feature.getGeometry(), { duration: 5000 }) });
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

  public nivel(valor: number): string {

    if (valor > 75.00)
      return 'p-badge-danger';

    if (valor > 50.00)
      return 'p-badge-warning';

    if (valor > 25.00)
      return 'p-badge-info';

    return 'p-badge-success';
  }

}
