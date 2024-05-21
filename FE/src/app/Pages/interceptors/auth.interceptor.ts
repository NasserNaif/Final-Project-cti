import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HTTP_INTERCEPTORS,
} from '@angular/common/http';

import {catchError, delay, finalize, map, Observable, throwError} from 'rxjs';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {


    if(window.localStorage.getItem("token") != undefined && window.localStorage.getItem("token")!= null){
      console.log("___________")

      request = request.clone({
        headers: request.headers.set('Authorization', 'Bearer '+window.localStorage.getItem("token")),
      });

    }


    console.log("_______ggg____")


    return next.handle(request).pipe(
      delay(0),
      finalize(() => console.log()),
    )
  }
}

export const AuthInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptor,
  multi: true,
};
