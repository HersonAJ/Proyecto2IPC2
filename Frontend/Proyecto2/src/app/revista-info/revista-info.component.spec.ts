import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistaInfoComponent } from './revista-info.component';

describe('RevistaInfoComponent', () => {
  let component: RevistaInfoComponent;
  let fixture: ComponentFixture<RevistaInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistaInfoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistaInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
