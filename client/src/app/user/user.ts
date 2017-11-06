export namespace accountFields {
  export type id_user = number;
  export type pseudo = string;
  export type mail = string | null;
  export type zip_code = number | null;
  export type city = string | null;
  export type adress = string | null;

}

export interface Account {
  id: accountFields.id_user;
  pseudo: accountFields.pseudo;
  mail: accountFields.mail;
  zip_code: accountFields.zip_code;
  city: accountFields.city;
  adress: accountFields.adress;
}


export class User implements Account {
  id: number;
  pseudo: string;
  mail: string;
  zip_code: number;
  city: string;
  adress: string;

  constructor(id_user: number, pseudo: string, mail: string, zip_code: number, city: string, adress: string) {
    this.id = id_user;
    this.pseudo = pseudo;
    this.mail = mail;
    this.zip_code = zip_code;
    this.city = city;
    this.adress = adress;
  }
}
