package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.Bet;
import generated.BetHome;
import tools.JSONConverter;

@WebServlet("/bets/*")
public class BetServlet extends Endpoint{

	private static final long serialVersionUID = 1L;
	private BetHome service = new BetHome(EntityHandler.em);
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getPathInfo();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(url==null || url.isEmpty())
		 {
			 //GET : api/bets
			response.getWriter().write(JSONConverter.convert(service.getAll()));
		 }
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getPathInfo();
		
		if(url==null || url.isEmpty())
		 {
			 //POST : api/bets
			Bet bet = new Bet();
			
			//bet.set...
			
			service.persist(bet);
		 }
	}
	
}
