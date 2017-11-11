import {Card} from './card/card'


export namespace deckFields {
  export type idDeck = number;
  export type name = string;
  export type cards = Card[];
}

export interface DeckInterface {
  idDeck: deckFields.idDeck;
  name: deckFields.name;
  cards: deckFields.cards;
}

 
export class Deck implements DeckInterface {
  idDeck: number;
  name: string;
  cards: Card[];
  constructor(idDeck: number, name: string, cards: Card[]) {
    this.idDeck = idDeck;
    this.name = name;
    this.cards = cards;
  }
}
