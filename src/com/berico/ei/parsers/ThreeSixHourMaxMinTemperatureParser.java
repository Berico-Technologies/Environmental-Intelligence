package com.berico.ei.parsers;

import static com.berico.ei.ConversionUtils.*;
import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;
import static com.berico.ei.parsers.ParserUtils.*;

public class ThreeSixHourMaxMinTemperatureParser implements EncodedWxElementParser {

	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isMaxOrMinTemperatureGroup(context.getCurrentElement());
	}

	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		double temperature = parseEncodedTemperature(
				context.getCurrentElement().substring(1));
		
		if(isMaxTemperatureGroup(context.getCurrentElement())){
			
			context
				.getObservation()
				.getTemperatures()
				.setThreeOrSixHourMaximumTemperature(
					fromC(temperature));
		}
		else {
			
			context
			.getObservation()
			.getTemperatures()
			.setThreeOrSixHourMinimumTemperature(
				fromC(temperature));
		}
	}

}
