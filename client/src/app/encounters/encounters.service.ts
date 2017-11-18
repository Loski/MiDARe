import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

import { Encounter } from './encounter';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class EncountersService {

  private encountersURL = 'http://localhost:8080/DAR/api/encounters';  // URL to web api

  constructor(
    private http: HttpClient
  ) { }
  /** GET users from the server */
  getEncounters(): Observable<Encounter[]> {
    return this.http.get<Encounter[]>(this.encountersURL)
      .pipe(
      catchError(this.handleError('getEncounters', []))
      );
  }

  /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
