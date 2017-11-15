export namespace cardFields {
  export type idCard = number;
  export type cardName = string;
  export type cardDescription = string;
  export type url = string;
  export type urlbig = string;
}

export interface CardInterface {
  idCard: cardFields.idCard;
  cardName: cardFields.cardName;
  cardDescription: cardFields.cardDescription;
  url: cardFields.url;
  urlbig: cardFields.urlbig;
  number: number;
}


export class Card implements CardInterface {
  number: number;
  urlbig: string;
  idCard: number;
  cardName: string;
  cardDescription: string;
  url: string;
  constructor(idCard: number, name: string, description: string, URL: string, urlBig:string, number:number) {
    this.idCard = idCard;
    this.cardName = name;
    this.cardDescription = description;
    this.url = URL;
    this.urlbig = urlBig;
    this.number = number;
  }
}
