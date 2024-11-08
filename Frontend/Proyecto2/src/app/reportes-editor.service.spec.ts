import { TestBed } from '@angular/core/testing';

import { ReportesEditorService } from './Services/reportes-editor.service';

describe('ReportesEditorService', () => {
  let service: ReportesEditorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportesEditorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
