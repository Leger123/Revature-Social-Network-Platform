import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomepagePageComponent } from './homepage-page.component';

describe('HomepagePageComponent', () => {
  let component: HomepagePageComponent;
  let fixture: ComponentFixture<HomepagePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomepagePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepagePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
