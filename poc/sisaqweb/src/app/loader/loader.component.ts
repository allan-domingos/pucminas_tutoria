import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { LoaderService } from './loader.service';


@Component({
  selector: 'sgm-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.css']
})
export class LoaderComponent implements OnInit {

  @ViewChild('loaderModal') loaderModal: ElementRef;

  constructor(private loaderService: LoaderService) { }

  ngOnInit(): void {
    this.loaderService.addListener((b: boolean) => {
      if (b) {
        this.abrir();
      } else {
        this.fechar();
      }
    });
  }

  private abrir(): void {
    if (this.loaderModal) {
      $(this.loaderModal.nativeElement).modal('show');
    }
  }

  private fechar(): void {
    if (this.loaderModal) {
      $(this.loaderModal.nativeElement).modal('hide');
    }
  }

}
