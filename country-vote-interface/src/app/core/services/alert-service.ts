import {Injectable, signal} from '@angular/core';
import {AlertOptions} from '../models/alert-options';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private readonly _alerts = signal<AlertOptions[]>([]);
  readonly alerts = this._alerts.asReadonly();

  show(header: string, body: string) {
    this._alerts().push({ header, body });
    this._alerts.update(alerts => [...alerts, { header, body }]);
  }

  remove(toast: AlertOptions) {
    this._alerts.update(alerts => alerts.filter(t => t != toast));
  }
}
