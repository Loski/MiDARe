package api.scheduler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.EntityHandler;
import generated.Encounter;
import generated.Sport;
import generated.Team;

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

		String date="", id_encounter_api="", state_encounter="";
		int score_team_1=0, score_team_2=0;
		Team team1=null, team2=null;

		for(Map.Entry<?, ?> node : json.entrySet())
		{
			if(node.getKey().equals("date"))
				date=(String) node.getValue();

			if(node.getKey().equals("games")){

				ArrayList<LinkedHashMap<?,?>> datagames =  (ArrayList<LinkedHashMap<?, ?>>) node.getValue();
				String line="";

				for(LinkedHashMap<?,?> nodeData : datagames){

					for(Map.Entry<?, ?> values :nodeData.entrySet()){

						if(values.getKey().equals("id")){
							System.out.println(line);
							id_encounter_api=(String) values.getValue();
							System.out.println("ID ENCOUNTER API:"+id_encounter_api);
							continue;
						}

						if(values.getKey().equals("status")){
							state_encounter=(String) values.getValue();
							continue;
						}

						if(values.getKey().equals("home_points")){
							score_team_1=(Integer) values.getValue();
							continue;
						}

						if(values.getKey().equals("away_points")){
							score_team_2=(Integer) values.getValue();
							continue;
						}

						if(values.getKey().equals("home")){

							LinkedHashMap<?,?> dataHome = (LinkedHashMap<?,?>) values.getValue();

							if(EntityHandler.teamService.findByIdApi((String)dataHome.get("id")).size() == 0){							
								team1 = new Team(EntityHandler.sportService.findById(1), (String)dataHome.get("name"), (String)dataHome.get("id")); 
								EntityHandler.teamService.persist(team1);
							}else
								team1= EntityHandler.teamService.findByIdApi((String)dataHome.get("id")).get(0);
							continue;
						}

						if(values.getKey().equals("away")){

							LinkedHashMap<?,?> dataHome = (LinkedHashMap<?,?>) values.getValue();
							if(EntityHandler.teamService.findByIdApi((String)dataHome.get("id")).size() == 0){
								team2 = new Team(EntityHandler.sportService.findById(1), (String)dataHome.get("name"), (String)dataHome.get("id"));
								EntityHandler.teamService.persist(team2);
							}else
								team2= EntityHandler.teamService.findByIdApi((String)dataHome.get("id")).get(0);
						}

						if(team1!=null && team2!=null && state_encounter!=null && !state_encounter.equals("")){
							DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
							Date dateEncounter = format.parse(date);
							
							
							
							if(EntityHandler.encounterService.findByIdApi(id_encounter_api, 1).size() == 0){

								Encounter encounter = new Encounter(EntityHandler.sportService.findById(1), 
										team1, team2, id_encounter_api, 
										score_team_1, score_team_2, state_encounter, dateEncounter, null);
								EntityHandler.encounterService.persist(encounter);
							}else{
								Encounter encounter = EntityHandler.encounterService.findByIdApi(id_encounter_api, 1).get(0);
								
								if(!encounter.getStateEncounter().equals(state_encounter)){
									encounter.setStateEncounter(state_encounter);
									encounter.setScoreTeam1(score_team_1);
									encounter.setScoreTeam2(score_team_2);
									
									EntityHandler.encounterService.persist(encounter);
								}
							}

							team1=null;
							team2=null;
							state_encounter="";
							
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

			Thread.sleep(2000);

			String dateDuJour = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			String urlDateDuJour = NBA_SCHEDULE.replace("date", dateDuJour);
			updateSportsNBA(urlDateDuJour);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}