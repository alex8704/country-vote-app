import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {AlertService} from '../services';
import {catchError, throwError} from 'rxjs';

export const httpErrorInterceptor: HttpInterceptorFn = (req, next) => {
  const alertService = inject(AlertService);

  return next(req).pipe(
    catchError((error: unknown) => {
      if (error instanceof HttpErrorResponse) {
        const message = (error as HttpErrorResponse).error?.message || error.message;
        alertService.show('Error', message);
      }

      return throwError(() => error);
    })
  );
};
