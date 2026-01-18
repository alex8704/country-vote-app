import {Voter} from './voter';

export interface Vote {
  id: number;
  voter: Voter;
  votedCountryId: number;
  voteDatetime: Date;
}
