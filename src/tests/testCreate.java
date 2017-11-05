package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import api.EntityHandler;
import generated.Account;
import generated.AccountHome;
import modelData.User;

public class testCreate {

	public static void main(String[] args)
	{		
		 	EntityManager em = EntityHandler.em;
		    AccountHome service = new AccountHome(em);

		    service.persist(new Account("User", "Pass"));

	}
}
