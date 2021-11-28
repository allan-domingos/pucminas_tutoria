import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Tile as TileLayer, Vector as VectorLayer } from 'ol/layer';
import { Vector as VectorSource, OSM } from 'ol/source';
import { Style } from 'ol/style';
import { MapService } from './map.service';
import { FullScreen } from 'ol/control';
import { DatePipe } from '@angular/common';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { Feature, Map, View } from 'ol';
import { Barragem } from 'src/app/model/barragem';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import Polygon from 'ol/geom/Polygon';
import GeometryLayout from 'ol/geom/GeometryLayout';
import SimpleGeometry from 'ol/geom/SimpleGeometry';

@Component({
  selector: 'sgm-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
  providers: [MapService, DatePipe]
})
export class MapComponent implements OnInit, AfterViewInit {

  public barragens: Array<SelectItem>;
  public barragem: Barragem;

  /* O objeto mapa que é visivel. */
  public map: Map;
  public view: View;

  /* A camada visivel do open street map. */
  private openStreetMap: TileLayer;

  /* A camada para desenhar os terrenos */
  private drawVector: VectorLayer;
  private drawSource: VectorSource;

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

    this.drawSource = new VectorSource();
    this.drawVector = new VectorLayer({
      source: this.drawSource
    })
  }

  public ngOnInit() {

    this.initData();

    this.view = new View({
      projection: 'EPSG:4326',
      center: [0, 0],
      zoom: 0
    });

    this.map = new Map({
      target: 'map',
      layers: [this.openStreetMap, this.drawVector],
      view: this.view
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
    if(this.barragem){
      let feature = new Feature({
        geometry: new Polygon(
          this.barragem.coordenadas,
          GeometryLayout.XY,
          [this.barragem.coordenadas.length]
        )
      });
      this.drawSource.addFeature(feature);
      this.flyTo(this.barragem.coordenadas, () => { this.view.fit( <SimpleGeometry> feature.getGeometry(), {duration: 5000}) });
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
