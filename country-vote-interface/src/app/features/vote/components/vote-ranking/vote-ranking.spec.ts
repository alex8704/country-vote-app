import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VoteRanking} from './vote-ranking';

describe('VoteRanking', () => {
  let component: VoteRanking;
  let fixture: ComponentFixture<VoteRanking>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VoteRanking]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoteRanking);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
