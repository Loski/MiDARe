package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.EncounterHome;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/encounters/*")
public class EncounterServlet extends Endpoint{

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = URLParser.parseOnToken(request.getPathInfo(),0);
		
		try
		{
			 if(url==null || url.isEmpty())
			 {
				//GET : api/encounters
					
				sendJSON(response, JSONConverter.convert(EntityHandler.encounterService.getAll()));
				return;
			 }
			 
			 response.sendError(404);
	 
		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}

	}
}
