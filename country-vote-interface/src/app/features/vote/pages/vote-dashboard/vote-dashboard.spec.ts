import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VoteDashboard} from './vote-dashboard';

describe('VoteDashboard', () => {
  let component: VoteDashboard;
  let fixture: ComponentFixture<VoteDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VoteDashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoteDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
