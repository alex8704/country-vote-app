import {CountryVoteCount, RegisterVoteRequest, Vote} from '../models';

export abstract class VotePort {
  abstract retrieveTopVotedCountries(): Promise<CountryVoteCount[]>;
  abstract registerVote(request: RegisterVoteRequest): Promise<Vote>;
}
