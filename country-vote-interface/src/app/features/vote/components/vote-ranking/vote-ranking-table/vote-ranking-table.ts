import {Component, input} from '@angular/core';
import {CountryVoteCount} from '../../../../../domain/vote/models';

@Component({
  selector: 'app-vote-ranking-table',
  imports: [],
  templateUrl: './vote-ranking-table.html',
  styleUrl: './vote-ranking-table.scss',
})
export class VoteRankingTable {
  data = input<CountryVoteCount[]>([]);

}
