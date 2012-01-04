package com.berico.ei.parsers;

public class ParserUtils {

	public static double parseEncodedTemperature(String temperature){
		
		double parsedTemperature = normalizeWxMeasurement(temperature.substring(1), 0.1d);
		
		if(temperature.startsWith("1")){
			parsedTemperature *= -1;
		}
		return parsedTemperature;
	}
	
	public static double parseEncodedPrecip(String precip){
		
		return normalizeWxMeasurement(precip, 0.01d);
	}
	
	public static double parseEncodedSnowMeasurement(String snowMeasurement){
		
		return normalizeWxMeasurement(snowMeasurement, 0.1d);
	}
	
	public static double parseEncodedMbPressure(String mbPressure){
		
		return normalizeWxMeasurement(mbPressure, 0.1d);
	}
	
	protected static double normalizeWxMeasurement(String value, double modifier){
		
		return Integer.parseInt(value) * modifier;
	}
	
	
}
