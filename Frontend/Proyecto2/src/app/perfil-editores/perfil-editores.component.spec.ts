import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerfilEditoresComponent } from './perfil-editores.component';

describe('PerfilEditoresComponent', () => {
  let component: PerfilEditoresComponent;
  let fixture: ComponentFixture<PerfilEditoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PerfilEditoresComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PerfilEditoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
