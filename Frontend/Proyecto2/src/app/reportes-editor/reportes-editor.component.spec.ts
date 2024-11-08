import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportesEditorComponent } from './reportes-editor.component';

describe('ReportesEditorComponent', () => {
  let component: ReportesEditorComponent;
  let fixture: ComponentFixture<ReportesEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportesEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportesEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
