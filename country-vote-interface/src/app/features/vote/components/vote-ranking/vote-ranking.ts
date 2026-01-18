import {Component, computed, inject, OnInit, resource, signal} from '@angular/core';
import {VoteRankingFilterBox} from './vote-ranking-filter-box/vote-ranking-filter-box';
import {VoteRankingTable} from './vote-ranking-table/vote-ranking-table';
import {Vote, VotedCountry} from '../../../../domain/vote/models';
import {VotePort} from '../../../../domain/vote/ports/vote-port';
import {EventBridge} from '../../../../core/services';
import {EventTypeEnum} from '../../../../core/enums';

@Component({
  selector: 'app-vote-ranking',
  imports: [
    VoteRankingFilterBox,
    VoteRankingTable
  ],
  templateUrl: './vote-ranking.html',
  styleUrl: './vote-ranking.scss',
})
export class VoteRanking implements OnInit {
  voteApiClient = inject(VotePort);
  eventBridge = inject(EventBridge);
  voteRankingResource = resource({
    params: () => ({}),
    loader: () => this.voteApiClient.retrieveTopVotedCountries()
  });
  searchTerm = signal('');
  voteRankingData = computed(() => {
    return (this.voteRankingResource.value() ?? [])
      .filter(countryVotedCount => this.filterCountry(countryVotedCount.votedCountry, this.searchTerm()))
  })

  ngOnInit(): void {
    this.subscribeToApplicationEvents();
  }

  changeSearchTerm(term: string) {
    this.searchTerm.set(term.toLowerCase());
  }

  private filterCountry(country: VotedCountry, searchTerm: string) {
    return this.searchTerm().length == 0 ||
      Object.values(country).some(countryProperty =>
        typeof countryProperty === 'string' &&
        countryProperty.toLowerCase().includes(searchTerm));
  }

  private subscribeToApplicationEvents() {
    this.eventBridge.subscribe<Vote>(EventTypeEnum.VOTE_REGISTERED, event => {
      this.voteRankingResource.reload();
    })
  }
}
