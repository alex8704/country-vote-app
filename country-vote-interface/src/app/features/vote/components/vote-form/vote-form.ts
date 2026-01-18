import {Component, inject, signal} from '@angular/core';
import {Country, RegisterVoteRequest, Vote} from '../../../../domain/vote/models';
import {email, FieldState, form, FormField, maxLength, required, submit} from '@angular/forms/signals';
import {FormsModule} from '@angular/forms';
import {NgbTypeahead} from '@ng-bootstrap/ng-bootstrap';
import {catchError, debounceTime, distinctUntilChanged, filter, finalize, Observable, of, switchMap, tap} from 'rxjs';
import {CountryPort, VotePort} from '../../../../domain/vote/ports';
import {fromPromise} from 'rxjs/internal/observable/innerFrom';
import {EventBridge} from '../../../../core/services';
import {EventTypeEnum} from '../../../../core/enums';

const EMPTY_FORM_MODEL: RegisterVoteRequest = {
  voter: {
    email: '',
    name: ''
  },
  countryAlpha3Code: ''
};

@Component({
  selector: 'app-vote-form',
  imports: [
    FormField,
    FormsModule,
    NgbTypeahead
  ],
  templateUrl: './vote-form.html',
  styleUrl: './vote-form.scss',
})
export class VoteForm {
  countryPort = inject(CountryPort);
  votePort = inject(VotePort);
  eventBridge = inject(EventBridge);

  selectedCountry = signal<Country | null>(null);
  countriesSearching = signal(false);
  formModel = signal(EMPTY_FORM_MODEL);
  voteForm = form(this.formModel, (schemaPath) => {
    required(schemaPath.voter.name, {message: 'Name is required'});
    maxLength(schemaPath.voter.name, 256, {message: 'Name is too large'})

    required(schemaPath.voter.email, {message: 'Email is required'});
    maxLength(schemaPath.voter.email, 256, {message: 'Name is too large'});
    email(schemaPath.voter.email, {message: 'Invalid email'});

    required(schemaPath.countryAlpha3Code, {message: 'Country is required'});
  });

  formFieldMustShowFeedback(field: FieldState<any>) {
    return field.touched() && field.invalid();
  }

  registerVote() {
    submit(this.voteForm, async () => {
      const vote = await this.sendVoteRegistration();
      this.resetForm();
      this.dispatchVoteRegisteredEvent(vote);
    })
  }

  countrySearch = (searchTerm$: Observable<string>) => {
    return searchTerm$.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      filter(term => !!term?.length),
      tap(() => this.countriesSearching.set(true)),
      switchMap((term) =>
        fromPromise(this.countryPort.searchCountries(term))
      ),
      finalize(() => this.countriesSearching.set(false)),
      catchError(() => of([]))
    );
  }

  formatCountryItem = (country: Country)=> {
    return country.commonName;
  }

  private async sendVoteRegistration() {
    return this.votePort.registerVote(this.voteForm().value());
  }

  private dispatchVoteRegisteredEvent(vote: Vote) {
    this.eventBridge.dispatchEvent({type: EventTypeEnum.VOTE_REGISTERED, payload: vote});
  }

  private resetForm() {
    this.voteForm().reset(EMPTY_FORM_MODEL);
    this.selectedCountry.set(null);
  }

}
