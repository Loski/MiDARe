package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.URLParser;

@WebServlet("/user/*")
public class UserInformation extends Route {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String param = URLParser.parseURL(request.getPathInfo());
		
		 if(param!=null)
		 {
			 response.setContentType("text/plain");
			 response.setCharacterEncoding("UTF-8");
			 response.getWriter().write(param);
			 
			 System.out.println(param);
		 }
		 else
		 {
			 response.setContentType("text/plain");
			 response.setCharacterEncoding("UTF-8");
			 response.getWriter().write("manque un param");
		 }
		
	}

}
