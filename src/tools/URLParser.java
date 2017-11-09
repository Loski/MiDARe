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
		return url.split("/")[index];
	}
	
	public static String parseOnToken(String str, int index)
	{
		//A changer selon la façon dont on envoie le token
		if(str == null)
			return "";
		return str.split("\\?token=")[index];
	}
}
