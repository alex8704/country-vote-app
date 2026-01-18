import {Component} from '@angular/core';
import {VoteForm} from '../../components/vote-form/vote-form';
import {VoteRanking} from '../../components/vote-ranking/vote-ranking';

@Component({
  selector: 'app-vote-dashboard',
  imports: [
    VoteForm,
    VoteRanking,
    VoteForm,
    VoteRanking
  ],
  templateUrl: './vote-dashboard.html',
  styleUrl: './vote-dashboard.scss',
})
export class VoteDashboard {
}
