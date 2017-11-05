package api;

import java.io.IOException;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.Account;
import generated.AccountHome;
import generated.Bet;
import generated.BetHome;
import generated.Team;
import modelData.User;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/users/*")
public class UserServlet extends Endpoint {
	private static final long serialVersionUID = 1L;
	
	private static final String ID ="^/[1-9][0-9]*";
	private static final String USER_URL= ID;
	private static final String USER_BETS_URL = USER_URL + "/bets";	
	private static final String USER_THIS_BET_URL = USER_BETS_URL + ID;

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String url = URLParser.parseOnToken(request.getPathInfo(),0);
		 System.out.println("mu url is:" + url);
		 EntityManager em = EntityHandler.em;
		 if(url==null || url.isEmpty())
		 {
			 //GET : api/users
		    JSONConverter.sendObjectAsJson(response, EntityHandler.accountService.getAll());
		    return;
		 }
		 else { 
			 int id = URLParser.getParameterOfURL(url,1);
			 
			 if(id <0)
			 {
				 response.sendError(404);
				 return;
			 }
			 
			 Account user = EntityHandler.accountService.findById(id);
			 
			 if(user==null)
			 {
				 response.sendError(404);
				 return;
			 }

			 if(url.matches(USER_URL)) {
					 JSONConverter.sendObjectAsJson(response,user);
					 return;
				 }else if(url.matches(USER_BETS_URL)) {
					 JSONConverter.sendObjectAsJson(response,EntityHandler.betService.getAllByUser(id));
					 return;
				 }else if(url.matches(USER_THIS_BET_URL)){
					 int id_bet = URLParser.getParameterOfURL(url,3);
					 
					 if(id_bet < 1)
					 {
						 response.sendError(404);
						 return;
					 }
					 
					 Bet bet = EntityHandler.betService.findById(id_bet);
					 if(bet.getAccountByIdUser1().getIdUser()==id || bet.getAccountByIdUser2().getIdUser()==id)
					 {
						 JSONConverter.sendObjectAsJson(response, EntityHandler.betService.findById(id_bet));
						 return;
					 }
					 
					 return;
				 }
			 }
		 
		 response.sendError(404);
			
	}
}
			 

