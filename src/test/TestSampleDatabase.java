package test;

import java.util.HashSet;

import api.EntityHandler;
import generated.Account;
import generated.Card;
import generated.Deck;
import generated.Inventory;
import generated.InventoryId;
import tools.SHA256;

public class TestSampleDatabase {

	public static void main(String[] args) {
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
		
		//TO BE CONTINUED
	}

}
