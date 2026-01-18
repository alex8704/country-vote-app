import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./features/vote/vote.routes')
        .then(m => m.routes),
  }
];
