import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

import { Deck } from './deck';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class DeckService {

  private decksURL = 'http://localhost:8080/DAR/api/users';  // URL to web api

  constructor(
    private http: HttpClient
  ) {}

  /** GET deck by id. Return `undefined` when id not found */
  getDeckNo404<Data>(id: number): Observable<Deck> {
    const url = `${this.decksURL}/?id=${id}/decks`;
    return this.http.get<Deck[]>(url)
      .pipe(
      map(decks => decks[0]), // returns a {0|1} element array
      tap(h => {
        const outcome = h ? `fetched` : `did not find`;
      }),
      catchError(this.handleError<Deck>(`getDeck id=${id}`))
      );
  }

  /** GET deck by user id. Will 404 if id not found */
  getDeck(id: number): Observable<Deck> {
    const url = `${this.decksURL}/${id}/decks`;
    return this.http.get<Deck>(url).pipe(
      catchError(this.handleError<Deck>(`getDeck id=${id}`))
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
