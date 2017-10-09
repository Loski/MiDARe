import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

public abstract class JSONConverter {

	public static String convert(Object obj)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);		
		
		try {
		    // convert user object to json string and return it 
		    return mapper.writeValueAsString(obj);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		    return "";
		}
	}
}
