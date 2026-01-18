import {Component, signal} from '@angular/core';
import {MainHeader} from './core/layout/main-header/main-header';
import {RouterOutlet} from '@angular/router';
import {AlertsContainer} from './shared/components/alerts-container/alerts-container';

@Component({
  selector: 'app-root',
  imports: [
    MainHeader,
    RouterOutlet,
    AlertsContainer
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('country-vote-interface');
}
