import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayFeedComponent } from './display-feed.component';

describe('DisplayFeedComponent', () => {
  let component: DisplayFeedComponent;
  let fixture: ComponentFixture<DisplayFeedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayFeedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayFeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
