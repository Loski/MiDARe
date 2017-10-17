package tools;

public abstract class URLParser {
	
	private static String[] parseUrlParameters(String url)
	{
		String[] parts = url.substring(1).split("/");
		
		if(parts.length>0)
		{
			return parts;
		}
		
		return null;
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("^[1-9]\\d*$");  //match a number with optional '-' and decimal.
	}
	
	public static int idOfElement(String url)
	{
		String[] parsedUrl = parseUrlParameters(url);
		
		
		System.out.println("PARSING : "+url);
		for(int i=0;i<parsedUrl.length;i++)
		{
			System.out.println(parsedUrl[i]);
		}
		
		if(parsedUrl!=null && isNumeric(parsedUrl[0]))
		{
			return Integer.parseInt(parsedUrl[0]);
		}
		
		return -1;	
	}
}
