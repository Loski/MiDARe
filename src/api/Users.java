package api;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.ws.rs.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

import generated.Account;
import generated.AccountHome;
import tools.JSONConverter;

@WebServlet("/api/users/*")
public class Users extends Endpoint{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Manager");
		EntityManager em = emf.createEntityManager();
		ObjectMapper mapper = new ObjectMapper();
	    AccountHome service = new AccountHome(em);

	    
	    
		String pathInfo = request.getPathInfo();
		
	    em.getTransaction().begin();
	    String jsonInString;
	    if(pathInfo == null || pathInfo.equals("/")){
			jsonInString = mapper.writeValueAsString(service.getAll());
		}else {
			JSONConverter.sendAsJson(response, service.findById((new Integer(pathInfo))));
		}
		
		System.out.println("je rentre");
	    em.getTransaction().commit();
	    em.close();
	    emf.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(request, response);
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(request, response);
	}

}
