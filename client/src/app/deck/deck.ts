import {Card} from './card/card'


export namespace deckFields {
  export type idDeck = number;
  export type name = string;
  export type cards = Card;
}

export interface DeckInterface {
  idDeck: deckFields.idDeck;
  name: deckFields.name;

}


export class Deck implements DeckInterface {

  constructor() {

  }
}
