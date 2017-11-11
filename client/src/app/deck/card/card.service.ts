import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

import { Card } from './card';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class CardService {

  private cardsURL = 'http://localhost:8080/DAR/cards';  // URL to web api

  constructor(
    private http: HttpClient
  ) { }
  /** GET users from the server */
  getCards(): Observable<Card[]> {
    return this.http.get<Card[]>(this.cardsURL)
      .pipe(
      catchError(this.handleError('getCards', []))
      );
  }

  /** GET user by id. Return `undefined` when id not found */
  getCardNo404<Data>(id: number): Observable<Card> {
    const url = `${this.cardsURL}/?id=${id}`;
    return this.http.get<Card[]>(url)
      .pipe(
      map(users => users[0]), // returns a {0|1} element array
      tap(h => {
        const outcome = h ? `fetched` : `did not find`;
      }),
      catchError(this.handleError<Card>(`getCard id=${id}`))
      );
  }

  /** GET user by id. Will 404 if id not found */
  getCard(id: number): Observable<Card> {
    const url = `${this.cardsURL}/${id}`;
    return this.http.get<Card>(url).pipe(
      catchError(this.handleError<Card>(`getCard id=${id}`))
    );
  }

  //////// Save methods //////////

  /** POST: add a new user to the server */
  addCard(user: Card): Observable<Card> {
    return this.http.post<Card>(this.cardsURL, user, httpOptions).pipe(
      catchError(this.handleError<Card>('addCard'))
    );
  }

  /** DELETE: delete the user from the server */
  deleteCard(user: Card | number): Observable<Card> {
    const id = typeof user === 'number' ? user : user.id;
    const url = `${this.cardsURL}/${id}`;

    return this.http.delete<Card>(url, httpOptions).pipe(
      catchError(this.handleError<Card>('deleteCard'))
    );
  }

  /** PUT: update the user on the server */
  updateCard(user: Card): Observable<any> {
    return this.http.put(this.cardsURL, user, httpOptions).pipe(
      catchError(this.handleError<any>('updateCard'))
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
