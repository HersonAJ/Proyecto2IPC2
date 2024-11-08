import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecargarCarteraEditorComponent } from './recargar-cartera-editor.component';

describe('RecargarCarteraEditorComponent', () => {
  let component: RecargarCarteraEditorComponent;
  let fixture: ComponentFixture<RecargarCarteraEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecargarCarteraEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecargarCarteraEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
