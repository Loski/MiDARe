package api.scheduler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DatabaseUpdater implements Runnable{

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
		String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		
		for(LinkedHashMap<?,?> node : json)
		{
			//System.out.println(node.get("realName_fr"));
		}
		
		//print result
		//System.out.println(indented);
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
