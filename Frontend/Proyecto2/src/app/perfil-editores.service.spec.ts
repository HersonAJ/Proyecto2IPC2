import { TestBed } from '@angular/core/testing';

import { PerfilEditoresService } from './Services/perfil-editores.service';

describe('PerfilEditoresService', () => {
  let service: PerfilEditoresService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PerfilEditoresService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
