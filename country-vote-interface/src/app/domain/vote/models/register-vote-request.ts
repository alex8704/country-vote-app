import {Voter} from './voter';

export interface RegisterVoteRequest {
  voter: Voter;
  countryAlpha3Code: string
}
