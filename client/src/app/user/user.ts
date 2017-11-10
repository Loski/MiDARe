export namespace accountFields {
  export type idUser = number;
  export type pseudo = string;
  export type password = string;
  export type mail = string | null;
  export type zipCode = number | null;
  export type city = string | null;
  export type adress = string | null;

}

export interface Account {
  id: accountFields.idUser;
  pseudo: accountFields.pseudo;
  password: accountFields.password;
  mail: accountFields.mail;
  zipCode: accountFields.zipCode;
  city: accountFields.city;
  adress: accountFields.adress;
}


export class User implements Account {
  id: number;
  pseudo: string;
  password: string;
  mail: string;
  zipCode: number;
  city: string;
  adress: string;

  constructor(idUser: number, pseudo: string, password: string, mail: string, zipCode: number, city: string, adress: string) {
    this.id = idUser;
    this.pseudo = pseudo;
    this.password = password;
    this.mail = mail;
    this.zipCode = zipCode;
    this.city = city;
    this.adress = adress;
  }
}
