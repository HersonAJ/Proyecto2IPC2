import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarCostoComponent } from './asignar-costo.component';

describe('AsignarCostoComponent', () => {
  let component: AsignarCostoComponent;
  let fixture: ComponentFixture<AsignarCostoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AsignarCostoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AsignarCostoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
