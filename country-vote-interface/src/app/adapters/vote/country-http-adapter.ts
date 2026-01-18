import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Country} from '../../domain/vote/models';
import {CountryPort} from '../../domain/vote/ports';
import {firstValueFrom} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CountryHttpAdapter implements CountryPort {
  httpClient = inject(HttpClient);

  searchCountries(searchTerm: string): Promise<Country[]> {
    return firstValueFrom(this.httpClient
      .get<Country[]>('/api/countries', {
        params: {searchTerm}
      }));
  }
}
