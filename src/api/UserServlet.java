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
import generated.BetHome;
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
		 
		 String url = request.getPathInfo();
		 System.out.println("mu url is:" + url);
		 EntityManager em = EntityHandler.em;
		 if(url==null || url.isEmpty())
		 {
			 //GET : api/users

			AccountHome service = new AccountHome(em);
		    JSONConverter.sendObjectAsJson(response, service.getAll());
		    return;
		 }
		 else { 
			 int id = URLParser.getParameterOfURL(url,1);
			 if(id > 0)
			 {
				 if(url.matches(USER_URL)) {
					 AccountHome service = new AccountHome(EntityHandler.em);
					 JSONConverter.sendObjectAsJson(response, service.findById(id));
				 }else if(url.matches(USER_BETS_URL)) {
					 BetHome service = new BetHome(em);
					 JSONConverter.sendObjectAsJson(response, service.getAllByUser(id));
				 }else if(url.matches(USER_THIS_BET_URL)){
					 BetHome service = new BetHome(em);
					 int id_bet = URLParser.getParameterOfURL(url,3);
					 JSONConverter.sendObjectAsJson(response, service.findById(id_bet));
				 }
			 }
			 else
			 {
				 response.sendError(404, "MALFORMATED URL");
			 }
		}
			
	}
}
			 

