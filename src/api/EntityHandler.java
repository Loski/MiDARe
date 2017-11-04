package api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import generated.SportHome;
import generated.TeamHome;

public abstract class EntityHandler {

	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Manager");
	public static EntityManager em = emf.createEntityManager();
	public static TeamHome teamService = new TeamHome(em);
	public static SportHome sportService = new SportHome(em);
}