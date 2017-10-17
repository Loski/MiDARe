package api;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelData.User;
import tools.JSONConverter;

@WebServlet("/auth")
public class Login extends Route {
	private static final long serialVersionUID = 1L;
      
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameterMap().containsKey("username"))
		{
			String username = request.getParameter( "username");
			
			User user = new User(username);
				
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JSONConverter.convert(user));
		}
		else
		{
			 response.sendError(422,"Le paramètre username est manquant");
		}
	}

}
