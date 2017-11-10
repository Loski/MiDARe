export type enum_state_bet = 'BEGIN' | 'WAITING' | 'VALID' | 'OVER';
export type enum_state_encounter = 'Soon' | 'Current' | 'Over';


export namespace betFields {
  export type id_bet = number;
  export type id_encounter = number;
  export type id_service_1 = number;
  export type id_service_2 = number;
  export type id_user_1 = number;
  export type id_user_2 = number;
  export type state_bet = enum_state_bet | null;
}

export interface bet {
  id_bet: betFields.id_bet;
  id_encounter: betFields.id_encounter;
  id_service_1: betFields.id_service_1;
  id_service_2: betFields.id_service_2;
  id_user_1: betFields.id_user_1;
  id_user_2: betFields.id_user_2;
  state_bet: betFields.state_bet;

}