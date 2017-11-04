package tests;

import java.util.List;

import javax.persistence.EntityManager;

import api.EntityHandler;
import generated.Account;
import generated.AccountHome;
import generated.Encounter;
import generated.Team;

public class testQuery {

	public static void main(String[] args)
	{		
		System.out.println(EntityHandler.teamService.findById((1)).getNameTeam());
		System.out.println(EntityHandler.teamService.getEncounters(1));

	}
}
