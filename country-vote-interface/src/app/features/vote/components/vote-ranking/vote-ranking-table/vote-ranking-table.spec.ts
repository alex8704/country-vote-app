import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VoteRankingTable} from './vote-ranking-table';

describe('VoteRankingTable', () => {
  let component: VoteRankingTable;
  let fixture: ComponentFixture<VoteRankingTable>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VoteRankingTable]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoteRankingTable);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
