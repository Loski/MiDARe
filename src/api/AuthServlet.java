package api;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.User;
import generated.Account;
import tools.JSONConverter;
import tools.JWT;
import tools.SHA256;

@WebServlet("/auth")
public class AuthServlet extends Endpoint {
	private static final long serialVersionUID = 1L;
      
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		if(request.getParameterMap().containsKey("pseudo") && request.getParameterMap().containsKey("password"))
		{
			try {
				
				List<Account> account = EntityHandler.accountService.getAccountWithPseudo(request.getParameter("pseudo"));
				
				if(!account.isEmpty()) {
					if(SHA256.sha256(request.getParameter("password")).equals(account.get(0).getPassword())){
						
						String token = JWT.createJWT(account.get(0).getIdUser().toString(), 10000);
							
						Map<String, Object> map = new HashMap<String,Object>();
						map.put("token", token);
						
						sendJSON(response, JSONConverter.convert(account.get(0), map));
						
					    return;
					}
					else{
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
						//response.getWriter().write("Utilisateur ou Mot de passe incorrect");
					}
				}
				else{
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					//response.getWriter().write("Utilisateur ou Mot de passe incorrect");
				}
				     
			}
			catch (Exception e) {System.out.println(e);response.sendError(500,e.getMessage());}
			
		}
		else{
			 response.sendError(422,"Le paramètre username ou password est manquant");
		}
	}
}