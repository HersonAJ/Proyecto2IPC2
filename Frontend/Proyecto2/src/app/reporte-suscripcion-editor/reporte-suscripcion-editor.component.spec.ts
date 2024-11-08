import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteSuscripcionEditorComponent } from './reporte-suscripcion-editor.component';

describe('ReporteSuscripcionEditorComponent', () => {
  let component: ReporteSuscripcionEditorComponent;
  let fixture: ComponentFixture<ReporteSuscripcionEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteSuscripcionEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteSuscripcionEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
