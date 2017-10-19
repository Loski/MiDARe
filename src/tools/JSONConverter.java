package tools;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class JSONConverter {

	public static String convert(Object obj)
	{
		ObjectMapper mapper = new ObjectMapper();
		//mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);	
		
		try {
		    // convert user object to json string and return it 
		    return mapper.writeValueAsString(obj);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		    return "{}";
		}
	}
	
	public static String convertWithMixin(Object obj,Class<?> mixin)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(obj.getClass(),mixin);
		
		try {
		    // convert user object to json string and return it 
		    return mapper.writeValueAsString(obj);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		    return "{}";
		}
	}
	
	public static String convert(Object obj, Map<String, Object> newFields)
	{
		ObjectMapper mapper = new ObjectMapper();
		//mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);	
		
		for(Map.Entry<String, Object> entry : newFields.entrySet())
		{
			try {
				ObjectNode json = (ObjectNode) mapper.readTree(convert(obj));
				
				json.set(entry.getKey(),mapper.readTree(convert(obj)));
				
				return json.toString();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "{}";
	}
	
}
