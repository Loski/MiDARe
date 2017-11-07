package api;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.Account;
import generated.AccountHome;
import generated.Bet;
import generated.BetHome;
import generated.Encounter;
import generated.Service;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/bets/*")
public class BetServlet extends Endpoint{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = URLParser.parseOnToken(request.getPathInfo(),0);
		
		try
		{
			if(url==null || url.isEmpty())
			{
				 //GET : api/bets
				
				sendJSON(response, JSONConverter.convert(EntityHandler.betService.getAll()));
				return;
			}
			
			response.sendError(404);
	 
		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//api/bets
		
		if(!request.getParameterMap().containsKey("id_encounter")
				|| !request.getParameterMap().containsKey("id_user_1") || !request.getParameterMap().containsKey("name_service")){
			
			response.sendError(422, "un paramètre est manquant");
			
		}else if(request.getParameter("name_service").length()>20){
			response.sendError(422, "nom du service excédant 20 caractères");
		
		}else{
			

			Account a1 = EntityHandler.accountService.findById(Integer.parseInt(request.getParameter("id_user_1")));
			Encounter e1 = EntityHandler.encounterService.findById(Integer.parseInt(request.getParameter("id_encounter")));

			Service serv = new Service(request.getParameter("name_service"), null, null);
			
			EntityHandler.serviceService.persist(serv);
						
			Bet bet = new Bet(null, a1, e1, null, serv, "BEGIN");
			EntityHandler.betService.persist(bet);
			
			response.getWriter().write("Création du pari réussi");
		}
	}
	
	
	
}
