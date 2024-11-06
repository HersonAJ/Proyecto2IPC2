import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrecioAnunciosComponent } from './precio-anuncios.component';

describe('PrecioAnunciosComponent', () => {
  let component: PrecioAnunciosComponent;
  let fixture: ComponentFixture<PrecioAnunciosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrecioAnunciosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrecioAnunciosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
