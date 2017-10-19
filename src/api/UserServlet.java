package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelData.User;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/user/*")
public class UserServlet extends Route {
	private static final long serialVersionUID = 1L;
	
	private static final String USER_URL="^/[1-9][0-9]*";
	private static final String USER_BETS_URL= USER_URL + "/bets";
    	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String url = request.getPathInfo();
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
		 
		
		 if(url==null || url.isEmpty())
		 {
			 //GET : api/user
			 
			 response.setContentType("text/plain");
			 response.setCharacterEncoding("UTF-8");
			 response.getWriter().write("FETCH LA LIST");
		 }
		 else if(url.matches(USER_URL))
		 {
			 //GET : api/user/{id}
			 
			 int id = URLParser.getParameterOfURL(url,1);
				
			 if(id!=-1)
			 {	
				 HashMap<String,Object> newFields = new HashMap<String,Object>();
				 User a = new User();
				 a.setUsername("Billy");
				 
				 newFields.put("other_user",a);
				 
				 String data = JSONConverter.convert(a, newFields);
				 
				 response.getWriter().write(data);
			 }
			 else
			 {
				 response.sendError(404,"MAFORMATED URL");
			 }
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
