import {Component, inject} from '@angular/core';
import {AlertService} from '../../../core/services';
import {NgbToast} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-alerts-container',
  imports: [
    NgbToast
  ],
  templateUrl: './alerts-container.html',
  styleUrl: './alerts-container.scss',
  host: { class: 'toast-container position-fixed top-0 end-0 p-3', style: 'z-index: 1200' }
})
export class AlertsContainer {
  alertService = inject(AlertService);
}
