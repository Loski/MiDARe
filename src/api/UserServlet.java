package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.URLParser;

@WebServlet("/user/*")
public class UserServlet extends Route {
	private static final long serialVersionUID = 1L;
	
	private static final String USER_URL="^/[1-9][0-9]*";
	private static final String USER_BETS_URL="^/[1-9][0-9]*/bets";
    	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String url = request.getPathInfo();
		
		 if(url==null || url.isEmpty() || url.equals("/"))
		 {
			 response.setContentType("text/plain");
			 response.setCharacterEncoding("UTF-8");
			 response.getWriter().write("FETCH LA LIST");
		 }
		 else if(url.matches(USER_URL))
		 {
			 int id = URLParser.getParameterOfURL(url,1);
				
			 if(id!=-1)
			 {			 
				 response.setContentType("text/plain");
				 response.setCharacterEncoding("UTF-8");
				 response.getWriter().write(""+id);
			 }
		 }
		 else if(url.matches(USER_BETS_URL))
		 {
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
