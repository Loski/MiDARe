package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.Team;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/sports/*")
public class SportServlet extends Endpoint{
	
	private static final String ID ="^/[1-9][0-9]*";
	private static final String SPORT_URL = ID;
	private static final String BETS_URL = "/bets";
	private static final String THIS_BETS_URL = BETS_URL + ID;
	private static final String ENCOUNTERS_URL = "/encounters";
	private static final String THIS_ENCOUNTERS_URL = ENCOUNTERS_URL +ID;
	private static final String TEAM_URL = SPORT_URL + "/teams";
	private static final String THIS_TEAM_URL = TEAM_URL + ID;
	private static final String TEAM_ENCOUNTERS_URL = THIS_TEAM_URL + "/encounters";
	private static final String TEAM_THIS_ENCOUNTER_URL = TEAM_ENCOUNTERS_URL + ID;
	private static final String TEAM_ENCOUNTER_BETS_URL = TEAM_THIS_ENCOUNTER_URL + "/bets";
	private static final String TEAM_ENCOUNTER_THIS_BETS_URL = TEAM_ENCOUNTER_BETS_URL + ID;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = URLParser.parseOnToken(request.getPathInfo(),0);
		
		try
		{
			 if(url==null || url.isEmpty())
			 {
				 //GET : api/sports
				 
				 String res = JSONConverter.convert(EntityHandler.sportService.getAll());
				 sendJSON(response, res);
				 return;
			 }else 
			 {				 
				 int id = URLParser.getParameterOfURL(url,1);
				 
				 if(id < 1)
				 {
					 response.sendError(404);
					 return;
				 }
				 
				 if(url.matches(SPORT_URL)){
					 String res = JSONConverter.convert(EntityHandler.sportService.findById(id));
					 sendJSON(response, res);
					 return;
				 }else if(url.matches(TEAM_URL)) {
					 String res = JSONConverter.convert(EntityHandler.sportService.findById(id).getTeams());
					 sendJSON(response, res);
					 return;
				 }else if(url.matches(THIS_TEAM_URL))
				 {
					 int idOfTeam = URLParser.getParameterOfURL(url,3);
					 
					 if(idOfTeam < 1)
					 {
						 response.sendError(404);
						 return;
					 }
					 
					 Team t = EntityHandler.teamService.findById(idOfTeam);
					 if(t.getSport().getIdSport()==id)
					 {
						 String res = JSONConverter.convert(t);
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
