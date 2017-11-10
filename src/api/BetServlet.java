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
}
