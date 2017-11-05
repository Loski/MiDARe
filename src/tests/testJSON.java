package tests;

import generated.Service;
import tools.JSONConverter;

public class testJSON {

	public static void main(String[] args)
	{
		System.out.println(JSONConverter.convert(new Service()));
	}
}
