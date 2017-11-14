package api.scheduler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.EntityHandler;
import generated.Card;

public class DatabaseTriadCardUpdater implements Runnable{

	public static final String TRIPLE_TRIAD_CARDS_URL = "http://ffxivtriad.com/api/cards";
	public static final String TRIPLE_TRIAD_THIS_CARD_URL = "http://ffxivtriad.com/api/cards/:id";
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	@SuppressWarnings("unchecked")
	private void updateCards() throws Exception
	{
		URL obj = new URL(TRIPLE_TRIAD_CARDS_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + TRIPLE_TRIAD_CARDS_URL);
		System.out.println("Response Code : " + responseCode);
		
		/*List<Card> cards = new LinkedList<Card>();

		    BufferedReader br = new BufferedReader(new  InputStreamReader(con.getInputStream()));
		    String json = "";
		    if(br != null){
		        json = br.readLine();
		    }

		    ObjectMapper mapper = new ObjectMapper();

		    cards = mapper.readValue(json, LinkedList.class);        
		    
		    System.out.println(cards.get(0));*/
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		ObjectMapper mapper = new ObjectMapper();
		ArrayList<LinkedHashMap<?,?>> json = (ArrayList<LinkedHashMap<?,?>>) mapper.readValue(response.toString(), ArrayList.class);
		//String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		
		for(LinkedHashMap<?,?> node : json)
		{
			Card c = new Card();
			
			c.setNumber((Integer)node.get("number"));
			c.setCardDescription((String) node.get("description"));
			c.setCardName((String) node.get("realName_fr"));
			c.setDeck(EntityHandler.deckService.findById(1));
			
			System.out.println(c);
			
			EntityHandler.cardService.persist(c);
		}
	}
	
	@Override
	public void run() {

		try {
			updateCards();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
