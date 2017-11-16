package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import generated.Encounter;
import generated.EncounterHome;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/encounters/*")
public class EncounterServlet extends Endpoint{

	private static final long serialVersionUID = 1L;
	private static final String NAME ="/^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$";
	private static final String ENCOUNTER_URL= NAME;
	
	private static final Log log = LogFactory.getLog(EncounterServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = URLParser.parseOnToken(request.getPathInfo(),0);
		
		try
		{
			 if(url==null || url.isEmpty())
			 {
				//GET : api/encounters
					
				 JSONConverter.sendObjectAsJson(response, EntityHandler.encounterService.getAll());
				return;
			 }
			 else
			 {
				int id = URLParser.getParameterOfURL(url,1);
				
				if(id <0)
				{
					response.sendError(404);
					return;
				}
				
				Encounter encounter = EntityHandler.encounterService.findById(id);
				
				if(encounter==null)
				{
					response.sendError(404);
					log.info("encounter not found");
					return;
				}
				
				JSONConverter.sendObjectAsJson(response,encounter);
			 }
	 
		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}

	}
}
