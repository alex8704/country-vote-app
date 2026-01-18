import {Routes} from '@angular/router';
import {VoteDashboard} from './pages/vote-dashboard/vote-dashboard';

export const routes: Routes = [
  {
    path: '**',
    component: VoteDashboard
  }
];
