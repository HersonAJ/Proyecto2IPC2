import { TestBed } from '@angular/core/testing';

import { RevistaSuscriptorService } from './Services/revista-suscriptor.service';

describe('RevistaSuscriptorService', () => {
  let service: RevistaSuscriptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RevistaSuscriptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
