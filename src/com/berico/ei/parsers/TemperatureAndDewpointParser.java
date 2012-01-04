package com.berico.ei.parsers;

import static com.berico.ei.ConversionUtils.*;
import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

public class TemperatureAndDewpointParser implements EncodedWxElementParser {


	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isTemperatureDewpointElement(context.getCurrentElement());
	}


	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		// If the temperature has already been set by another parser
		// (e.g.: hourly temperature and dewpoint group), don't continue
		// executing.
		if(context
			.getObservation()
			.getTemperatures()
			.getAmbientAirTemperature() != null){
			
			return;
		}
		
		String[] parts = context.getCurrentElement().split("/");
		
		int temperature = parseTempElement(parts[0]);
		int dewpoint = parseTempElement(parts[1]);
		
		context
			.getObservation()
			.getTemperatures()
			.setAmbientTemperature(
				fromC(temperature));
		
		context
			.getObservation()
			.getTemperatures()
			.setDewpoint(
				fromC(dewpoint));
	}
	
	protected static int parseTempElement(String element){
		
		int sign = 1;
		
		String modElement = element;
		
		if(element.startsWith("M")){
			
			modElement = element.substring(1);
			sign = -1;
		}
		
		int value = Integer.parseInt(modElement);
		
		return value * sign;
	}

}
