import {CountryPort, VotePort} from '../domain/vote/ports';
import {CountryHttpAdapter, VoteHttpAdapter} from './vote';

export const ADAPTERS_PROVIDERS = [
  {
    provide: VotePort,
    useExisting: VoteHttpAdapter
  },
  {
    provide: CountryPort,
    useExisting: CountryHttpAdapter
  }
];
