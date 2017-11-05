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

@WebServlet("/services/*")
public class ServiceServlet extends Endpoint{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = URLParser.parseOnToken(request.getPathInfo(),0);

		try
		{
			 if(url==null || url.isEmpty())
			 {
				 //GET : api/services
				 
				 String res = JSONConverter.convert(EntityHandler.serviceService.getAll());
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
