package com.berico.ei.parsers;

public class ParserUtils {

	public static double parseEncodedTemperture(String temperature){
		
		double parsedTemperature = Integer.parseInt(temperature.substring(1)) * 0.1d;
		
		if(temperature.startsWith("1")){
			parsedTemperature *= -1;
		}
		return parsedTemperature;
	}
	
}
