package api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class EntityHandler {

	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Manager");
	public static EntityManager em = emf.createEntityManager();
	
}
