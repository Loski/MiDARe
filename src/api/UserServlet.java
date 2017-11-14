package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import generated.Account;
import generated.AccountHome;
import generated.Bet;
import generated.Card;
import generated.Encounter;
import generated.Inventory;
import generated.InventoryId;
import tools.JSONConverter;
import tools.JWT;
import tools.SHA256;
import tools.URLParser;

@WebServlet("/api/users/*")
public class UserServlet extends Endpoint {
	private static final long serialVersionUID = 1L;

	private static final String ID ="/[1-9][0-9]*";
	private static final String USER_URL= ID;
	private static final String USER_BETS_URL = USER_URL + "/bets";
	private static final String USER_DECK_URL = USER_URL + "/decks";	
	private static final String USER_THIS_BET_URL = USER_BETS_URL + ID;
	private static final Log log = LogFactory.getLog(UserServlet.class);


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String url = URLParser.parseOnToken(request.getPathInfo(),0);
		System.out.println("my url is:" + url);

		try
		{
			if(url==null || url.isEmpty())
			{
				//GET : api/users
				JSONConverter.sendObjectAsJson(response, EntityHandler.accountService.getAll());
				log.info("api/users");
				return;
			}
			else { 
				int id = URLParser.getParameterOfURL(url,1);

				if(id <0)
				{
					response.sendError(404);
					return;
				}

				Account user = EntityHandler.accountService.findById(id);

				if(user==null)
				{
					response.sendError(404);
					log.info("user not found");
					return;
				}

				if(url.matches(USER_URL)) {
					JSONConverter.sendObjectAsJson(response,user);
					log.info(USER_URL);
					return;
				}else if(url.matches(USER_BETS_URL)) {
					log.info(USER_BETS_URL);
					JSONConverter.sendObjectAsJson(response,EntityHandler.betService.getAllByUser(id));
					return;
				}else if(url.matches(USER_THIS_BET_URL)){
					log.info(USER_THIS_BET_URL);
					int id_bet = URLParser.getParameterOfURL(url,3);
					if(id_bet < 1)
					{
						response.sendError(404);
						return;
					}

					Bet bet = EntityHandler.betService.findById(id_bet);
					if(bet.getCreator().getIdUser()==id || bet.getOpponent().getIdUser()==id)
					{
						JSONConverter.sendObjectAsJson(response, EntityHandler.betService.findById(id_bet));
						return;
					}
					return;
				}else if(url.matches(USER_DECK_URL)) {
					log.info(USER_DECK_URL);
					JSONConverter.sendObjectAsJson(response, user.getInventories());
					return;
				}
			}
			log.info("Route not found");

			response.sendError(404);

		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = URLParser.parseOnToken(request.getPathInfo(),0);

		try
		{
			if(url==null || url.isEmpty())
			{
				//POST : api/users

				createUser(request, response);
				return;
			}
			else
			{
				//POST : api/users/{id}/bets
				if(url.matches(USER_BETS_URL))
				{
					//if(user existe + si j'ai les droits)

					createBet(request, response);

				}else if(url.matches(USER_THIS_BET_URL)){

					int id_bet = URLParser.getParameterOfURL(url,3);
					modifyBet(request, response, id_bet);

				}else{
					response.sendError(422,"paramètre manquant");
				}
			}

		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}

	}


	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = URLParser.parseOnToken(request.getPathInfo(),0);

		try
		{	
			if(url==null || url.isEmpty())
			{
				//PUT : api/users
				response.sendError(404);
				return;
			}
			else if(url.matches(USER_URL))
			{
				int id = URLParser.getParameterOfURL(url,1);
				modifyUser(request, response, id);
				return;
			}
			//PUT : api/users/{id}/bets/{id}
			else if(url.matches(USER_THIS_BET_URL))
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String data = br.readLine();			
				if(data.split("=")[0].equals("accept")){
					int id_bet = URLParser.getParameterOfURL(url,3);
					if(data.split("=")[1].equals("true")){
						acceptBet(request, response, id_bet);
					}else if(data.split("=")[1].equals("false")){
						refuseBet(request, response, id_bet);
					}else
						response.sendError(422,"le paramètre accept est mal rempli.");	
				}else
					response.sendError(422,"le paramètre accept n'est pas transmis.");
			}
			//POST : api/users/{id}/bets
			else if(url.matches(USER_BETS_URL))
			{
				if(request.getParameter("accept").equals("true")){
					int id_bet = URLParser.getParameterOfURL(url,3);
					acceptBet(request, response, id_bet);
				}else{
					response.sendError(422,"le paramètre accept est faux.");
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			response.sendError(500,e.getMessage());
		}
		
		response.sendError(404);

	}
	
	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = URLParser.parseOnToken(request.getPathInfo(),0);

		try
		{
			if(url==null || url.isEmpty())
			{
				//DELETE : api/users/

				response.sendError(404);
				return;
			}
			else
			{
				//DELETE : api/users/{id}/
				if(url.matches(USER_URL))
				{
					//if(user existe + si j'ai les droits)

					int id = URLParser.getParameterOfURL(url,1);
					deleteUser(request, response, id);
					return;

				}else if(url.matches(USER_THIS_BET_URL)){

				}
			}

		}catch(Exception e)
		{
			response.sendError(500,e.getMessage());
		}
		
		response.sendError(404);

	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
		
		if(id <0)
		{
			response.sendError(404);
			return;
		}

		Account user = EntityHandler.accountService.findById(id);

		if(user==null)
		{
			response.sendError(404);
			log.info("user not found");
			return;
		}
		
		EntityHandler.accountService.remove(user);

	}

	private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Account user = JSONConverter.deserialize(request.getInputStream(),Account.class);

			/*if(!EntityHandler.accountService.getAccountWithPseudo(request.getParameter("pseudo")).isEmpty()){
				response.sendError(422, "pseudo déja utilisé");
			}
			else if(request.getParameter("pseudo").length()>20){
				response.sendError(422, "pseudo trop long (moins de 20 caractères)");

			}
			else if(request.getParameter("mail").length()>50) {
				response.sendError(422, "mail trop long (moins de 50 caractères)");
			}
			else if(request.getParameter("adress").length()>50) {
				response.sendError(422, "adress trop long (moins de 50 caractères)");
			}
			else if(request.getParameter("city").length()>50) {
				response.sendError(422, "city trop long (moins de 50 caractères)");
			}
			else {*/
			user.setIdUser(null);
			user.setPassword(SHA256.sha256(user.getPassword()));
			
			EntityHandler.accountService.persist(user);
			String token = JWT.createJWT(user.getIdUser().toString(), 10000);
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("token", token);
			
			int nbCard = EntityHandler.cardService.getNumberOfCards();
			
			List<Integer> already_have = new LinkedList<Integer>(); 
			for(int i=1;i<=5;i++)
			{
				int index = -1;
				do 
				{
					index = (int)(Math.random() * nbCard);
				}while(already_have.contains(index));
				 
				already_have.add(index);
				
				Card card = EntityHandler.cardService.findById(i);
				
				InventoryId invID = new InventoryId(user.getIdUser(),card.getIdCard());
				Inventory inv = new Inventory(invID, user, card, 1);
				
				EntityHandler.inventoryService.persist(inv);
			}

			EntityHandler.accountService.refresh(user);
			
			sendJSON(response, JSONConverter.convert(user, map));
	}
	
	private void modifyUser(HttpServletRequest request, HttpServletResponse response, int id) throws Exception
	{
		System.out.println("Modify User");
		Account user = JSONConverter.deserialize(request.getInputStream(),Account.class);
		user.setIdUser(id);
		
		Account userBefore = EntityHandler.accountService.findById(id);
		
		if(userBefore==null)
		{
			response.sendError(404);
			return;
		}
		
		if(user.getPassword()!=null)
		{
			String pass = SHA256.sha256(user.getPassword());
			user.setPassword(pass);
		}
		else
		{
			user.setPassword(userBefore.getPassword());
		}
		
		EntityHandler.accountService.merge(user);
		
		sendJSON(response, JSONConverter.convert(user));
	}

	private void createBet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//TODO :Changer moi ça en JSON jeunes gens !!!

		if(!request.getParameterMap().containsKey("id_encounter")
				|| !request.getParameterMap().containsKey("id_user_1") || !request.getParameterMap().containsKey("name_service")){

			response.sendError(422, "un param�tre est manquant");

		}else if(request.getParameter("name_service").length()>20){
			response.sendError(422, "nom du service exc�dant 20 caract�res");

		}else{


			Account a1 = EntityHandler.accountService.findById(Integer.parseInt(request.getParameter("id_user_1")));
			Encounter e1 = EntityHandler.encounterService.findById(Integer.parseInt(request.getParameter("id_encounter")));

			String service = request.getParameter("name_service");

			Bet bet = new Bet(a1, null,null,null,e1, null,null, "BEGIN");
			EntityHandler.betService.persist(bet);

			response.getWriter().write("Création du pari réussi");
		}
	}

	//another user proposes his service
	private void modifyBet(HttpServletRequest request, HttpServletResponse response, int id_bet) throws IOException{

		//TODO: changer en json

		if(!request.getParameterMap().containsKey("id_user_2") || !request.getParameterMap().containsKey("name_service")){

			response.sendError(422, "un paramètre est manquant");
		}else if(request.getParameter("name_service").length()>20){
			response.sendError(422, "nom du service excédant 20 caract�res");

		}else if(EntityHandler.betService.findById(id_bet).getStateBet().equals("BEGIN")){


			Account a1 = EntityHandler.accountService.findById(Integer.parseInt(request.getParameter("id_user_2")));

			Bet bet = EntityHandler.betService.findById(id_bet);
			bet.setOpponent(a1);

			bet.setStateBet("WAITING");

			EntityHandler.betService.persist(bet);

			response.getWriter().write("Pari dans l'état d'attente");

		}else{
			response.sendError(422, "status du bet n'est pas dans l'état BEGIN");
		}
	}

	//the creator confirms the bet
	private void acceptBet(HttpServletRequest request, HttpServletResponse response, int id_bet) throws IOException{

		if(EntityHandler.betService.findById(id_bet).getStateBet().equals("WAITING")){

			Bet bet = EntityHandler.betService.findById(id_bet);
			bet.setStateBet("VALID");

			EntityHandler.betService.persist(bet);

			response.getWriter().write("Pari accepté");

		}else{
			response.sendError(422, "status du bet n'est pas dans l'état WAITING");
		}
	}
	
	//the creator refuses the bet
	//TO COMPLETE
	private void refuseBet(HttpServletRequest request, HttpServletResponse response, int id_bet) throws IOException{
		
		if(EntityHandler.betService.findById(id_bet).getStateBet().equals("WAITING")){

			Bet bet = EntityHandler.betService.findById(id_bet);
			bet.setStateBet("BEGIN");
			bet.setOpponent(null);

			EntityHandler.betService.persist(bet);

			response.getWriter().write("Pari accepté");

		}else{
			response.sendError(422, "status du bet n'est pas dans l'état WAITING");
		}
	}
}



