import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteComentariosEditorComponent } from './reporte-comentarios-editor.component';

describe('ReporteComentariosEditorComponent', () => {
  let component: ReporteComentariosEditorComponent;
  let fixture: ComponentFixture<ReporteComentariosEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteComentariosEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteComentariosEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
