import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteMegustaEditorComponent } from './reporte-megusta-editor.component';

describe('ReporteMegustaEditorComponent', () => {
  let component: ReporteMegustaEditorComponent;
  let fixture: ComponentFixture<ReporteMegustaEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteMegustaEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteMegustaEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
