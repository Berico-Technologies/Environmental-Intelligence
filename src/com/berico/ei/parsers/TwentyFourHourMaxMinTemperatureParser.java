package com.berico.ei.parsers;

import static com.berico.ei.ConversionUtils.*;
import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;
import static com.berico.ei.parsers.ParserUtils.*;

public class TwentyFourHourMaxMinTemperatureParser implements
		EncodedWxElementParser {

	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isTwentyFourHourMaxMinTemperature(context.getCurrentElement());
	}

	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		double maxTemp = parseEncodedTemperature(context.getCurrentElement().substring(1, 5));
		double minTemp = parseEncodedTemperature(context.getCurrentElement().substring(5));
		
		context
			.getObservation()
			.getTemperatures()
			.setTwentyFourHourMaximumTemperature(
				fromC(maxTemp));
		
		context
		.getObservation()
		.getTemperatures()
		.setTwentyFourHourMinimumTemperature(
			fromC(minTemp));
	}

}