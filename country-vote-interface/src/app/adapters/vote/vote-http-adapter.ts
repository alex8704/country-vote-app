import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {firstValueFrom} from 'rxjs';
import {CountryVoteCount, RegisterVoteRequest, Vote} from '../../domain/vote/models';
import {VotePort} from '../../domain/vote/ports/vote-port';

@Injectable({
  providedIn: 'root'
})
export class VoteHttpAdapter implements VotePort {
  httpClient = inject(HttpClient);

  retrieveTopVotedCountries() {
    return firstValueFrom(this.httpClient
      .get<CountryVoteCount[]>('/api/voting/ranking'));
  }

  registerVote(request: RegisterVoteRequest) {
    return firstValueFrom(this.httpClient
      .post<Vote>('/api/voting', request));
  }
}
