package api;


import java.io.IOException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.Account;
import modelData.User;
import tools.JSONConverter;
import tools.JWT;
import tools.SHA256;

@WebServlet("/auth")
public class Login extends Endpoint {
	private static final long serialVersionUID = 1L;
      
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameterMap().containsKey("username") && request.getParameterMap().containsKey("password"))
		{
			try {
				
				EntityHandler.em.getTransaction().begin();
				List<Account> account = EntityHandler.em.createQuery("SELECT a FROM Account a WHERE a.pseudo LIKE :inputPseudo")
						.setParameter("inputPseudo", request.getParameter("username")).getResultList();
				EntityHandler.em.getTransaction().commit();
				
				if(!account.isEmpty()) {
					if(SHA256.sha256(request.getParameter("password")).equals(account.get(0).getPassword())){
						
						String token = JWT.createJWT(account.get(0).getIdUser().toString(), 10000);
						
						String username = request.getParameter( "username");
						User user = new User();
						user.setUsername(username);
						user.setToken(token);
							
					    response.setContentType("application/json");
					    response.setCharacterEncoding("UTF-8");
					    response.getWriter().write(JSONConverter.convert(user));
					}
					else{
						response.getWriter().write("Mot de passe incorrect");
					}
				}
				else{
					response.getWriter().write("Utilisateur incorrect");
				}
				     
			}
			catch (Exception e) {System.out.println(e);}
			
		}
		else{
			 response.sendError(422,"Le paramètre username ou password est manquant");
		}
	}
}