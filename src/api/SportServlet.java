package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.JSONConverter;

@WebServlet("/sports/*")
public class SportServlet extends Endpoint{
	
	private static final String ID ="^/[1-9][0-9]*";
	private static final String SPORT_URL = ID;
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = request.getPathInfo();

		try
		{
			 if(url==null || url.isEmpty())
			 {
				 //GET : api/sports
				 String res = JSONConverter.convert(EntityHandler.teamService.getAll());
				 sendJSON(response, res);
			 }
			 else
			 {
				 response.sendError(404,"MAFORMATED URL");
			 }
		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}
	}
	
	
}
