package com.berico.ei.parsers;


import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.isDateTimeElement;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class ObservationTimeParser implements EncodedWxElementParser {


	public void performParse(EncodedWxStringParseContext context) throws EncodedWxElementParseException {
		
		String observationTime = context.getCurrentElement();
		
		if(observationTime.trim().length() != 7){
			throw new EncodedWxElementParseException(
					"Unable to parse the Observation Time from the Weather Element: element size was not 7 characters.", this, context);
		}
		
		try {
			
			int day = Integer.parseInt(observationTime.substring(0, 2));
			int hour = Integer.parseInt(observationTime.substring(2, 4));
			int minute = Integer.parseInt(observationTime.substring(4, 6));
		
			DateTime obDateTime = new DateTime(context.getObservationYear(), context.getObservationMonth(), day, hour, minute, 0, 0, DateTimeZone.UTC);

			context.getObservation().setTimeOfObservation(obDateTime);
			
		} catch (Exception e){
			
			throw new EncodedWxElementParseException(
					String.format("Unable to parse the Observation Time from the Weather Element: %s", e.getMessage()), this, context);
		}
	}


	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		return isDateTimeElement(context.getCurrentElement());
	}

}
