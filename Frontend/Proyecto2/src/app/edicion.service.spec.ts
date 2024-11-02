import { TestBed } from '@angular/core/testing';

import { EdicionService } from './Services/edicion.service';

describe('EdicionService', () => {
  let service: EdicionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EdicionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
