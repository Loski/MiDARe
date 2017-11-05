package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.AccountHome;
import generated.Bet;
import generated.BetHome;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/bets/*")
public class BetServlet extends Endpoint{

	private static final long serialVersionUID = 1L;
	
	private static final String ID ="^/[1-9][0-9]*";
	private static final String BETS_URL= ID;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = URLParser.parseOnToken(request.getPathInfo(),0);
		
		if(url==null || url.isEmpty())
		{
			 //GET : api/bets
			
			sendJSON(response, JSONConverter.convert(EntityHandler.betService.getAll()));
		}
		else
		{
			 int id = URLParser.getParameterOfURL(url,1);
			 if(id > 0)
			 {
				 if(url.matches(BETS_URL)) {
					 sendJSON(response, JSONConverter.convert(EntityHandler.betService.findById(id)));
				 }
			 }
			 else
			 {
				 response.sendError(404);
			 }
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getPathInfo();
		
		if(url==null || url.isEmpty())
		 {
			 //POST : api/bets
		 }
	}
	
}
