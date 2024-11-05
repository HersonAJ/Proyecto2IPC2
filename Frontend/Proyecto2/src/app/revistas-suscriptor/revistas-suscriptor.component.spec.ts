import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistasSuscriptorComponent } from './revistas-suscriptor.component';

describe('RevistasSuscriptorComponent', () => {
  let component: RevistasSuscriptorComponent;
  let fixture: ComponentFixture<RevistasSuscriptorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistasSuscriptorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistasSuscriptorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
