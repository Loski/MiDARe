export namespace cardFields {
  export type idCard = number;
  export type cardName = string;
  export type cardDescription = string;
  export type url = string;
  export type urlBig = string;
}

export interface CardInterface {
  id: cardFields.idCard;
  cardName: cardFields.cardName;
  description: cardFields.cardDescription;
  url: cardFields.url;
  urlBig: cardFields.urlBig;
  number: number;
}


export class Card implements CardInterface {
  number: number;
  urlBig: string;
  id: number;
  cardName: string;
  description: string;
  url: string;
  constructor(id: number, name: string, description: string, URL: string, urlBig:string, number:number) {
    this.id = id;
    this.cardName = name;
    this.description = description;
    this.url = URL;
    this.urlBig = urlBig;
    this.number = number;
  }
}
