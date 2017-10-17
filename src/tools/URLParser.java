package tools;

public abstract class URLParser {

	
	public static int getParameterOfURL(String url, int index)
	{
		return Integer.parseInt(parseURL(url, index));
	}
	
	public static String parseURL(String url, int index)
	{
		return url.split("/")[index];
	}
}
