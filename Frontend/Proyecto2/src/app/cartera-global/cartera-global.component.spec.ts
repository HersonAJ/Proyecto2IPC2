import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarteraGlobalComponent } from './cartera-global.component';

describe('CarteraGlobalComponent', () => {
  let component: CarteraGlobalComponent;
  let fixture: ComponentFixture<CarteraGlobalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarteraGlobalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarteraGlobalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
