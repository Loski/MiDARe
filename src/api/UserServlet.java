package api;

import java.io.IOException;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.Account;
import generated.AccountHome;
import modelData.User;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/users/*")
public class UserServlet extends Endpoint {
	private static final long serialVersionUID = 1L;
	
	private static final String USER_URL="^/[1-9][0-9]*";
	private static final String USER_BETS_URL= USER_URL + "/bets";
    	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String url = request.getPathInfo();

		
		 if(url==null || url.isEmpty())
		 {
			 //GET : api/user

			AccountHome service = new AccountHome(EntityHandler.em);
		    JSONConverter.sendObjectAsJson(response, service.getAll());
		    return;
		 }
		 else if(url.matches(USER_URL))
		 {
			 //GET : api/user/{id}
			 
			 int id = URLParser.getParameterOfURL(url,1);
				
			 if(id > -1)
			 {	
				 AccountHome service = new AccountHome(EntityHandler.em);
				 JSONConverter.sendObjectAsJson(response, service.findById(id));
			 }
			 else
			 {
				 response.sendError(404,"MAFORMATED URL");
			 }
			 return;
		 }
		 else if(url.matches(USER_BETS_URL))
		 {
			//GET : api/user/{id}/bets
			 
			 int id = URLParser.getParameterOfURL(url,1);
				
			 if(id!=-1)
			 {			 
				 response.setContentType("text/plain");
				 response.setCharacterEncoding("UTF-8");
				 response.getWriter().write("BETS FROM "+id);
			 }
		 }
		 else
		 {
			 response.setContentType("text/plain");
			 response.setCharacterEncoding("UTF-8");
			 
			 response.getWriter().write("MAFORMATED");
		 }
		
	}

}
