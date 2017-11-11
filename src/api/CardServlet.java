package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.Account;
import generated.Card;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/cards/*")
public class CardServlet extends Endpoint{

	private static final long serialVersionUID = 1L;
	
	private static final String ID ="^/[1-9][0-9]*";
	private static final String CARD_URL = ID;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = URLParser.parseOnToken(request.getPathInfo(),0);
		
		try
		{
			if(url==null || url.isEmpty())
			{
				 //GET : api/cards
				
				sendJSON(response, JSONConverter.convert(EntityHandler.cardService.getAll()));
				return;
			}
			else if(url.matches(CARD_URL))
			{
				int id = URLParser.getParameterOfURL(url,1);

				if(id <0)
				{
					response.sendError(404);
					return;
				}

				Card card = EntityHandler.cardService.findById(id);

				if(card==null)
				{
					response.sendError(404);
					return;
				}
				
				sendJSON(response, JSONConverter.convert(card));
				return;
			}
			
			response.sendError(404);
	 
		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}
	}	
}
