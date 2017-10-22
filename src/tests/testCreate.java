package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import generated.Account;
import generated.AccountHome;
import modelData.User;

public class testCreate {

	public static void main(String[] args)
	{		
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccountHome");
		 EntityManager em = emf.createEntityManager();
		    AccountHome service = new AccountHome(em);

		    em.getTransaction().begin();
		    service.persist(new Account("User", "Pass"));
		    em.getTransaction().commit();
		    
		    em.close();
		    emf.close();
	}
}
