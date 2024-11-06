import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportePopularAdminComponent } from './reporte-popular-admin.component';

describe('ReportePopularAdminComponent', () => {
  let component: ReportePopularAdminComponent;
  let fixture: ComponentFixture<ReportePopularAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportePopularAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportePopularAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
