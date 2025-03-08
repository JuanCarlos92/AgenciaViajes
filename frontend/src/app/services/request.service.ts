import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpErrorResponse, HttpHeaders, HttpParams, HttpEvent } from '@angular/common/http';
import { Observable, Observer } from 'rxjs';
import { filter } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class RequestService {
  constructor(private httpClient: HttpClient, private router: Router) {}

  request(requestServiceOptions: RequestServiceOptions, adminRequest: boolean, loader?: boolean): Observable<any> {
    let headers = new HttpHeaders();

    if (requestServiceOptions.headers) {
      headers = requestServiceOptions.headers;
    }
    headers = headers.append('Content-Type', 'application/json');
    headers = headers.append('Accept', '*/*');
    headers = headers.append('Access-Control-Allow-Origin', '*');
    headers = headers.append('Cache-Control', 'no-cache');

    if (localStorage.getItem('token')) {
      headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
    }

    requestServiceOptions.headers = headers;

    // Construir el objeto HttpRequest
    const httpRequest: HttpRequest<any> =
      this.buildHttRequestObjectFromRequestServiceOptions(requestServiceOptions);

    const customObservable = new Observable((observer: Observer<any>) => {
      this.httpClient
        .request(httpRequest)
        .pipe(
          filter((event: HttpEvent<any>): event is HttpResponse<any> => event instanceof HttpResponse) // Filtrar solo HttpResponse
        )
        .subscribe(
          (res: HttpResponse<any>) => {
            observer.next(res.body);
          },
          (res: HttpErrorResponse) => {
            observer.error(res);
            observer.complete();
          },
          () => {
            observer.complete();
          }
        );
    });

    return customObservable;
  }

  private buildHttRequestObjectFromRequestServiceOptions(requestServiceOptions: RequestServiceOptions): HttpRequest<any> {
    const initOptions: any = {};

    if (requestServiceOptions.headers) {
      initOptions.headers = requestServiceOptions.headers;
    }
    if (requestServiceOptions.reportProgress) {
      initOptions.reportProgress = requestServiceOptions.reportProgress;
    }
    if (requestServiceOptions.params) {
      initOptions.params = requestServiceOptions.params;
    }
    if (requestServiceOptions.responseType) {
      initOptions.responseType = requestServiceOptions.responseType;
    }
    if (requestServiceOptions.withCredentials) {
      initOptions.withCredentials = requestServiceOptions.withCredentials;
    }

    const httpRequest: HttpRequest<any> = new HttpRequest(
      requestServiceOptions.method,
      requestServiceOptions.url,
      requestServiceOptions.body,
      initOptions
    );

    return httpRequest;
  }
}

export interface RequestServiceOptions {
  body?: any;
  headers?: HttpHeaders;
  reportProgress?: boolean;
  withCredentials?: boolean;
  responseType: 'arraybuffer' | 'blob' | 'json' | 'text';
  method: string;
  params?: HttpParams;
  url: string;
}
