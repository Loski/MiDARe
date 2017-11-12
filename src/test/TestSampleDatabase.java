package test;

import api.EntityHandler;
import generated.*;
import tools.SHA256;

public class TestSampleDatabase {

	public static void main(String[] args) {
		
		try
		{
		Account acc = new Account("pseudoTest",SHA256.sha256("pass"), "mon_mail",999, "ma_ville","mon_adresse");
		EntityHandler.accountService.persist(acc);
		Deck d = new Deck("Fatalax Trading Card");
		EntityHandler.deckService.persist(d);
		Card c = new Card(d, "First_Card","ma_description");
		c.setNumber(555);
		EntityHandler.cardService.persist(c);
		InventoryId invId = new InventoryId(acc.getIdUser(), c.getIdCard());
		Inventory inv = new Inventory(invId, acc, c, 1000);
		EntityHandler.inventoryService.persist(inv);
		
		Sport sport = new Sport("Pétanque");
		EntityHandler.sportService.persist(sport);
		Team team1 = new Team(sport,"Team Cancer", null);
		Team team2 = new Team(sport,"Team PLS",null);
		EntityHandler.teamService.persist(team1);
		EntityHandler.teamService.persist(team2);
		Encounter enc = new Encounter(sport, team1, team2, 0, 0,null); //Rajouter state + date
		EntityHandler.encounterService.persist(enc);
		
		//TO BE CONTINUED
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
