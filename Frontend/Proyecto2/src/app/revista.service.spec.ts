import { TestBed } from '@angular/core/testing';

import { RevistaService } from './Services/revista.service';

describe('RevistaService', () => {
  let service: RevistaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RevistaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
