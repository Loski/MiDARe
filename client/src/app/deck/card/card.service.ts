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
  /** GET cards from the server */
  getCards(): Observable<Card[]> {
    return this.http.get<Card[]>(this.cardsURL)
      .pipe(
      catchError(this.handleError('getCards', []))
      );
  }

  /** GET card by id. Return `undefined` when id not found */
  getCardNo404<Data>(id: number): Observable<Card> {
    const url = `${this.cardsURL}/?id=${id}`;
    return this.http.get<Card[]>(url)
      .pipe(
      map(cards => cards[0]), // returns a {0|1} element array
      tap(h => {
        const outcome = h ? `fetched` : `did not find`;
      }),
      catchError(this.handleError<Card>(`getCard id=${id}`))
      );
  }

  /** GET card by id. Will 404 if id not found */
  getCard(id: number): Observable<Card> {
    const url = `${this.cardsURL}/${id}`;
    return this.http.get<Card>(url).pipe(
      catchError(this.handleError<Card>(`getCard id=${id}`))
    );
  }



  /** PUT: update the card on the server */
  updateCard(card: Card): Observable<any> {
    return this.http.put(this.cardsURL, card, httpOptions).pipe(
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
