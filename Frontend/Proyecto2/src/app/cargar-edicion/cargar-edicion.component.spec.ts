import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CargarEdicionComponent } from './cargar-edicion.component';

describe('CargarEdicionComponent', () => {
  let component: CargarEdicionComponent;
  let fixture: ComponentFixture<CargarEdicionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CargarEdicionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CargarEdicionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
