import { TestBed } from '@angular/core/testing';

import { RevistaInfoService } from './Services/revista-info.service';

describe('RevistaInfoService', () => {
  let service: RevistaInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RevistaInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
