import {Country, Voter} from '../../../../domain/vote/models';

export interface VoteFormModel {
  voter: Voter,
  country?: Country
}

