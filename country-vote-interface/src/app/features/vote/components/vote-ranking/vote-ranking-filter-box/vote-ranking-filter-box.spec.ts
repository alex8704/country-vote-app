import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VoteRankingFilterBox} from './vote-ranking-filter-box';

describe('VoteRankingFilterBox', () => {
  let component: VoteRankingFilterBox;
  let fixture: ComponentFixture<VoteRankingFilterBox>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VoteRankingFilterBox]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoteRankingFilterBox);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
