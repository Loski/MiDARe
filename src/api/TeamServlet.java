package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.AccountHome;
import generated.Team;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/teams/*")
public class TeamServlet extends Endpoint{

	private static final long serialVersionUID = 1L;
	private static final String TEAM_URL="^/[1-9][0-9]*";
	private static final String TEAM_ENCOUNTERS_URL= TEAM_URL + "/encounters";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = URLParser.parseOnToken(request.getPathInfo(),0);

		try
		{
			 if(url==null || url.isEmpty())
			 {
				 //GET : api/teams
				 
				 String res = JSONConverter.convert(EntityHandler.teamService.getAll());
				 sendJSON(response, res);
				 return;
			 }
			 else if(url.matches(TEAM_ENCOUNTERS_URL))
			 {
				 //GET : api/teams/{id}/encounters
				 
				 int id = URLParser.getParameterOfURL(url,1);
				
				 if(id < 1)
				 {
					 response.sendError(404);
					 return;
				 }
				 
				 String res = JSONConverter.convert(EntityHandler.teamService.getEncounters(id));
				 sendJSON(response, res);
				 return;
			 }

			 response.sendError(404); 
			 
		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}
	}
}
