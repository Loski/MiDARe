package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.URLParser;

/**
 * Servlet implementation class User
 */
@WebServlet("/user/*")
public class UserInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInformation() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
