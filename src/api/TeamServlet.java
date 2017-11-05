package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.Encounter;
import generated.Team;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/teams/*")
public class TeamServlet extends Endpoint{

	private static final long serialVersionUID = 1L;
	
	
	//TODO : A bouger dans SportServlet !!!
	
	private static final String ID = "^/[1-9][0-9]*";
	private static final String TEAM_URL= ID;
	private static final String TEAM_ENCOUNTERS_URL= TEAM_URL + "/encounters";
	private static final String TEAM_THIS_ENCOUNTERS_URL = TEAM_ENCOUNTERS_URL + ID;
	private static final String TEAM_ENCOUNTERS_BETS_URL= TEAM_THIS_ENCOUNTERS_URL + "/bets";
	
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
			 else
			 {
				 //GET : api/teams/{id}/...				 
				 int id = URLParser.getParameterOfURL(url,1);
				
				 if(id < 1)
				 {
					 response.sendError(404);
					 return;
				 }
				 
				 Team t = EntityHandler.teamService.findById(id);
				 
				 if(t==null)
				 {
					 response.sendError(404);
					 return;
				 }
				 
				 if(url.matches(TEAM_ENCOUNTERS_URL))
				 {
					//GET : api/teams/{id}/encounters	
					 
					 String res = JSONConverter.convert(t.getEncounters());
					 sendJSON(response, res);
					 return;
				 }
				 else 
				 {
					//GET : api/teams/{id}/encounters/...
					 
					 int idOfEnc = URLParser.getParameterOfURL(url,3);
					 
					 if(idOfEnc < 1)
					 {
						 response.sendError(404);
						 return;
					 }
					 
					 Encounter enc = EntityHandler.encounterService.findById(idOfEnc);
					 
					 if(enc == null)
					 {
						 response.sendError(404);
						 return;
					 }
					 
					 if(url.matches(TEAM_THIS_ENCOUNTERS_URL))
					 {
						//GET : api/teams/{id}/encounters/{id}
						 
						 String res = JSONConverter.convert(enc);
						 sendJSON(response, res);
						 return;
					 }
					 else if(url.matches(TEAM_ENCOUNTERS_BETS_URL))
					 {
						//GET : api/teams/{id}/encounters/{id}/bets
						 
						 String res = JSONConverter.convert(enc.getBets());
						 sendJSON(response, res);
						 return;
					 }
				 }
				 
			 }

			 response.sendError(404); 
			 
		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}
	}
}
