package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Endpoint extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	    
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
	}
	
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	    
	}
	
	
}
