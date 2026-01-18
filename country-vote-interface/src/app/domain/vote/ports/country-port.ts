import {Country} from '../models';

export abstract class CountryPort {
  abstract searchCountries(searchTerm: string): Promise<Country[]>;
}
