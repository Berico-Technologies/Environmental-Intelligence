package com.berico.ei.parsers;

import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

import javax.measure.Measurable;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;

import org.jscience.physics.amount.Amount;

import com.berico.ei.Winds;

public class WindsParser implements EncodedWxElementParser {


	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isWindsElement(context.getCurrentElement());
	}


	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		if(context.getObservation().getWinds() == null){
			
			context.getObservation().setWinds(new Winds());
		}
		
		String directionStr = context.getCurrentElement().substring(0, 3);
		
		if(directionStr.equals("VRB")){
			
			context.getObservation().getWinds().setIsVariable(true);
		}
		else {
			
			context.getObservation().getWinds()
				.setWindDirection(parseDirection(directionStr));
		}
		
		String windSpeedStr = context.getCurrentElement().substring(3).replace("KT", "");
		
		if(windSpeedStr.contains("G")){
			
			String[] windParts = windSpeedStr.split("G");
			
			context.getObservation().getWinds().setWindSpeed(parseSpeed(windParts[0]));
			
			context.getObservation().getWinds().setWindGust(parseSpeed(windParts[1]));
		} else {
			
			context.getObservation().getWinds().setWindSpeed(parseSpeed(windSpeedStr));
		}
		
	}
	
	private Measurable<Angle> parseDirection(String windDirectionStr){
		int direction = Integer.parseInt(windDirectionStr);
		return Amount.valueOf(direction, NonSI.DEGREE_ANGLE);
	}

	private Measurable<Velocity> parseSpeed(String windSpeedStr){
		int windSpeed = Integer.parseInt(windSpeedStr);
		return Amount.valueOf(windSpeed, NonSI.KNOT);
	}
	
}
