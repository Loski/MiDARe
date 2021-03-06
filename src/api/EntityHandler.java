package api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import generated.AccountHome;
import generated.BetHome;
import generated.CardHome;
import generated.DeckHome;
import generated.EncounterHome;
import generated.InventoryHome;
import generated.SportHome;
import generated.TeamHome;

public class EntityHandler {

	
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Manager");
	public static EntityManager em = emf.createEntityManager();
	public static AccountHome accountService = new AccountHome(em);
	public static TeamHome teamService = new TeamHome(em);
	public static SportHome sportService = new SportHome(em);
	public static BetHome betService = new BetHome(em);
	public static EncounterHome encounterService = new EncounterHome(em);
	public static CardHome cardService = new CardHome(em);
	public static DeckHome deckService = new DeckHome(em);
	public static InventoryHome inventoryService = new InventoryHome(em);
	
	/*Singleton ?*/
	
	/*private EntityHandler()
	{
		
	}
	
	private static class EntityHandlerHolder
	{		
		private final static EntityHandler instance = new EntityHandler();
	}
	
	public static EntityHandler getInstance()
	{
		return EntityHandlerHolder.instance;
	}*/
}