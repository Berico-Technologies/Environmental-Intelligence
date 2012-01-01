package com.berico.ei.parsers;

import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

import javax.measure.unit.SI;

import org.jscience.physics.amount.Amount;

public class TemperatureAndDewpointParser implements EncodedWxElementParser {


	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isTemperatureDewPointElement(context.getCurrentElement());
	}


	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		String[] parts = context.getCurrentElement().split("/");
		
		int temperature = parseTempElement(parts[0]);
		int dewpoint = parseTempElement(parts[1]);
		
		context.getObservation().setAirTemperature(Amount.valueOf(temperature, SI.CELSIUS));
		context.getObservation().setDewPoint(Amount.valueOf(dewpoint, SI.CELSIUS));
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
