import {Injectable} from '@angular/core';
import {ApplicationEvent} from '../models';
import {filter, Subject} from 'rxjs';
import {EventTypeEnum} from '../enums';

@Injectable({
  providedIn: 'root'
})
export class EventBridge {
  eventSubject$ = new Subject<ApplicationEvent<any>>();

  dispatchEvent<T>(event: ApplicationEvent<T>) {
    this.eventSubject$.next(event);
  }

  subscribe<T>(eventType: EventTypeEnum, subscriber: (event: ApplicationEvent<T>) => void) {
    return this.eventSubject$
      .pipe(
        filter(event => event.type === eventType)
      ).subscribe(subscriber);
  }
}
