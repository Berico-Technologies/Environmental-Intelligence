package com.berico.ei.parsers;

import static com.berico.ei.ConversionUtils.*;
import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

import static com.berico.ei.parsers.ParserUtils.*;

public class TemperatureAndDewpointGroupParser implements EncodedWxElementParser {

	
	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isTemperatureDewpointGroupElement(context.getCurrentElement());
	}

	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		double temperature = parseEncodedTemperature(
				context
					.getCurrentElement()
					.substring(1, 5));
		
		double dewpoint = parseEncodedTemperature(
				context
					.getCurrentElement()
					.substring(5));
		
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

}
