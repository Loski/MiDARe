export namespace cardFields {
  export type idCard = number;
  export type cardName = string;
  export type cardDescription = string;
  export type url = string;
  export type urlbig = string;
}

export interface CardInterface {
  id: cardFields.idCard;
  cardName: cardFields.cardName;
  cardDescription: cardFields.cardDescription;
  url: cardFields.url;
  urlbig: cardFields.urlbig;
  number: number;
}


export class Card implements CardInterface {
  number: number;
  urlbig: string;
  id: number;
  cardName: string;
  cardDescription: string;
  url: string;
  constructor(id: number, name: string, description: string, URL: string, urlBig:string, number:number) {
    this.id = id;
    this.cardName = name;
    this.cardDescription = description;
    this.url = URL;
    this.urlbig = urlBig;
    this.number = number;
  }
}
