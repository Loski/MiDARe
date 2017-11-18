import {Team} from './team';

export namespace encounterFields {
  export type idEncounter = number; 
  export type teamByIdTeam1 = Team;
  export type teamByIdTeam2 = Team;
  export type scoreTeam1 = number;
  export type scoreTeam2 = number;
  export type dateEncounter = Date;
}

export interface EncounterInterface {
  idEncounter: encounterFields.idEncounter;
  teamByIdTeam1: encounterFields.teamByIdTeam1;
  teamByIdTeam2: encounterFields.teamByIdTeam2;
  scoreTeam1: encounterFields.scoreTeam1;
  scoreTeam2: encounterFields.scoreTeam2;
  dateEncounter: encounterFields.dateEncounter;
}


export class Encounter implements EncounterInterface {
  idEncounter: encounterFields.idEncounter;
  teamByIdTeam1: encounterFields.teamByIdTeam1;
  teamByIdTeam2: encounterFields.teamByIdTeam2;
  scoreTeam1: encounterFields.scoreTeam1;
  scoreTeam2: encounterFields.scoreTeam2;
  dateEncounter: encounterFields.dateEncounter;

  constructor(idEncounter: number, teamByIdTeam1 :Team, teamByIdTeam2:Team, scoreTeam1:number, scoreTeam2:number, dateEncounter:Date){
  
  }
}
