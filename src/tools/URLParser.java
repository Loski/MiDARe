package tools;

public abstract class URLParser {

	
	public static int getParameterOfURL(String url, int index)
	{
		try
		{
			return Integer.parseInt(parseURL(url, index));
			
		}catch(Exception e)
		{
			return -1;
		}
	}
	
	public static String parseURL(String url, int index)
	{
		return parseOnToken(url.split("/")[index],0);
	}
	
	public static String parseOnToken(String str, int index)
	{
		//A changer selon la façon dont on envoie le token
		return str.split("\\?token=")[index];
	}
}
