package com.berico.ei.parsers;

import static com.berico.ei.ConversionUtils.*;
import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;
import static com.berico.ei.parsers.ParserUtils.*;

public class SnowDepthGroupParser implements EncodedWxElementParser {

	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isSnowDepthElement(context.getCurrentElement());
	}

	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		double snowDepth = parseEncodedSnowMeasurement(
								context.getCurrentElement().substring(2));
		
		context
			.getObservation()
			.getPrecipitation()
			.setSnowDepthOnGround(
				fromIn(snowDepth));
	}

}
