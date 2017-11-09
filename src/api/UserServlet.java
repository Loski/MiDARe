package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.Account;
import generated.Bet;
import generated.Encounter;
import generated.Service;
import tools.JSONConverter;
import tools.SHA256;
import tools.URLParser;

@WebServlet("/api/users/*")
public class UserServlet extends Endpoint {
	private static final long serialVersionUID = 1L;
	
	private static final String ID ="^/[1-9][0-9]*";
	private static final String USER_URL= ID;
	private static final String USER_BETS_URL = USER_URL + "/bets";	
	private static final String USER_THIS_BET_URL = USER_BETS_URL + ID;

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		System.out.println("my request" + request);
		System.err.println("my request path" + request.getPathInfo());
		 String url = URLParser.parseOnToken(request.getPathInfo(),0);
		 System.out.println("my url is:" + url);
		 
		 try
		{
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
		 
		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}
			
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = URLParser.parseOnToken(request.getPathInfo(),0);
		
		try
		{
			if(url==null || url.isEmpty())
			 {
				 //POST : api/users
			    
				createUser(request, response);
			    return;
			 }
			else
			{
				//POST : api/users/{id}/bets
				if(url.matches(USER_BETS_URL))
				{
					//if(user existe + si j'ai les droits)
					
					createBet(request, response);
				}
			}
		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}
			
	}
	
	private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		//TODO :Changer moi ça en JSON jeunes gens !!!
		if(!request.getParameterMap().containsKey("pseudo") || !request.getParameterMap().containsKey("password") ||
		!request.getParameterMap().containsKey("mail") || !request.getParameterMap().containsKey("zipcode") ||
		!request.getParameterMap().containsKey("city") || !request.getParameterMap().containsKey("adress")){
			response.sendError(422, "un paramètre est manquant");
		}
		else{
			//TODO :Check si c'est vraiment du 422 l'erreur
			
			if(!EntityHandler.accountService.getAccountWithPseudo(request.getParameter("pseudo")).isEmpty()){
				response.sendError(422, "pseudo déja utilisé");
			}
			else if(request.getParameter("pseudo").length()>20){
				response.sendError(422, "pseudo trop long (moins de 20 caractères)");
				
			}
			else if(request.getParameter("mail").length()>50) {
				response.sendError(422, "mail trop long (moins de 50 caractères)");
			}
			else if(request.getParameter("adress").length()>50) {
				response.sendError(422, "adress trop long (moins de 50 caractères)");
			}
			else if(request.getParameter("city").length()>50) {
				response.sendError(422, "city trop long (moins de 50 caractères)");
			}
			else {
				Account a = new Account(request.getParameter("pseudo"), SHA256.sha256(request.getParameter("password")),
						request.getParameter("mail"), Integer.parseInt(request.getParameter("zipcode")), 
						request.getParameter("city"), request.getParameter("adress"), null, null);
				EntityHandler.accountService.persist(a);
				response.getWriter().write("Création du compte réussi");
			}	
		}
	}
	
	private void createBet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//TODO :Changer moi ça en JSON jeunes gens !!!
		
		if(!request.getParameterMap().containsKey("id_encounter")
				|| !request.getParameterMap().containsKey("id_user_1") || !request.getParameterMap().containsKey("name_service")){
			
			response.sendError(422, "un param�tre est manquant");
			
		}else if(request.getParameter("name_service").length()>20){
			response.sendError(422, "nom du service exc�dant 20 caract�res");
		
		}else{
			

			Account a1 = EntityHandler.accountService.findById(Integer.parseInt(request.getParameter("id_user_1")));
			Encounter e1 = EntityHandler.encounterService.findById(Integer.parseInt(request.getParameter("id_encounter")));

			Service serv = new Service(request.getParameter("name_service"), null, null);
			
			EntityHandler.serviceService.persist(serv);
						
			Bet bet = new Bet(null, a1, e1, null, serv, "BEGIN");
			EntityHandler.betService.persist(bet);
			
			response.getWriter().write("Cr�ation du pari r�ussi");
		}
	}
}
			 

