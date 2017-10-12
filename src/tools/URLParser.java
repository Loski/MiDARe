package tools;

public abstract class URLParser {

	public static String parseURL(String url)
	{
		String[] parts = url.split("/");
		
		System.out.println("Parsing URL : "+url);
		
		for(int i=0;i<parts.length;i++)
		{
			System.out.print(i+" : ");
			System.out.println(parts[i]);
		}
		
		if(parts.length>0)
		{
			String param1 = parts[1];
			
			
			return param1;
		}
		
		return null;

	}
}
