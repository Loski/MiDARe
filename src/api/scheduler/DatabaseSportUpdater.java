package api.scheduler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DatabaseSportUpdater implements Runnable{

	public static final String SOCCER_EU_SCHEDULE = "https://api.sportradar.us/soccer-t3/eu/en/schedules/2017-11-06/results.json?api_key=c9eywn9u9j7knkqnxnevw5pk";
	public static final String SOCCER_EU_RESULT = "https://api.sportradar.us/soccer-t3/eu/en/schedules/2017-11-06/schedule.json?api_key=c9eywn9u9j7knkqnxnevw5pk";
	public static final String NBA_SCHEDULE = " http://api.sportradar.us/nba/trial/v4/en/games/date/schedule.json?api_key=zzx8pemft4ta7njwf8nqegqs";

	private final String USER_AGENT = "Mozilla/5.0";

	//TODO precise the date
	@SuppressWarnings("unchecked")
	private void updateSportsNBA(String urlDate) throws Exception{


		URL obj = new URL(urlDate);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();

		System.out.println("\nSending 'GET' request to URL : " + urlDate);
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
		LinkedHashMap<?,?> json = (LinkedHashMap<?,?>) mapper.readValue(response.toString(), LinkedHashMap.class);
		//String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

		for(Map.Entry<?, ?> node : json.entrySet())
		{
			if(node.getKey().equals("date"))
				System.out.println(node.getValue());

			if(node.getKey().equals("games")){

				ArrayList<LinkedHashMap<?,?>> datagames =  (ArrayList<LinkedHashMap<?, ?>>) node.getValue();
				String line="";

				for(LinkedHashMap<?,?> nodeData : datagames){

					for(Map.Entry<?, ?> values :nodeData.entrySet()){

						if(values.getKey().equals("id")){
							System.out.println(line);
							line=(String) values.getValue()+"|";

							continue;
						}

						if(values.getKey().equals("status")){
							line+=values.getValue()+"|";
							continue;
						}

						if(values.getKey().equals("home_points")){
							line+=values.getValue()+"|";
							continue;
						}

						if(values.getKey().equals("away_points")){
							line+=values.getValue()+"|";
							continue;
						}

						if(values.getKey().equals("home")){

							LinkedHashMap<?,?> dataHome = (LinkedHashMap<?,?>) values.getValue();
							line+=dataHome.get("name")+"|";
							line+=dataHome.get("id")+"|";
							continue;
						}

						if(values.getKey().equals("away")){

							LinkedHashMap<?,?> dataHome = (LinkedHashMap<?,?>) values.getValue();
							line+=dataHome.get("name")+"|";
							line+=dataHome.get("id")+"|";
						}
					}
				}
			}
		}
	}

	@Override
	public void run() {
		try {

			Date d = new Date();
			d.setTime(d.getTime() - 86400000);
			String dateDeVeille = new SimpleDateFormat("yyyy/MM/dd").format(d);
			String urlDateDeVeille = NBA_SCHEDULE.replace("date", dateDeVeille);
			updateSportsNBA(urlDateDeVeille);

			
			String dateDuJour = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			String urlDateDuJour = NBA_SCHEDULE.replace("date", dateDuJour);
			updateSportsNBA(urlDateDuJour);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
