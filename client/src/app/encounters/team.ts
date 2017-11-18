export namespace TeamFields {
  export type idTeam = number;
  export type nameTeam = string;

}

export interface TeamInterface {
  idTeam: TeamFields.idTeam;
  nameTeam: TeamFields.nameTeam;
}


export class Team implements TeamInterface {
    idTeam : number;
    nameTeam : string;
    constructor(idTeam: number, nameTeam:string) {

    }
}  
