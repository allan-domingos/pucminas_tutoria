import { TestBed } from '@angular/core/testing';

import { AquisicaoService } from './aquisicao.service';

describe('AquisicaoService', () => {
  let service: AquisicaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AquisicaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
