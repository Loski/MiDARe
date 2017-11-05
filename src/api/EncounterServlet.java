package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.EncounterHome;
import tools.JSONConverter;

@WebServlet("/encounters/*")
public class EncounterServlet extends Endpoint{

	private static final long serialVersionUID = 1L;

	private EncounterHome service = new EncounterHome(EntityHandler.em);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = request.getPathInfo();
		if(url==null || url.isEmpty())
		{
			//GET : api/encounters
		   super.sendJSON(response, JSONConverter.convert(service.getAll()));			
		}else{
			response.sendError(404,"MAFORMATED URL");
		}
	}
}
