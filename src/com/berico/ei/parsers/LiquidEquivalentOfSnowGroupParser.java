package com.berico.ei.parsers;

import static com.berico.ei.ConversionUtils.*;
import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;
import static com.berico.ei.parsers.ParserUtils.*;

public class LiquidEquivalentOfSnowGroupParser implements
		EncodedWxElementParser {

	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isLiquidEquivalentOfSnowElement(context.getCurrentElement());
	}

	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
	
		double liquidEquivalentOfSnow = parseEncodedSnowMeasurement(
											context.getCurrentElement().substring(3));
		
		context
			.getObservation()
			.getPrecipitation()
			.setLiquidEquivalentOfSnowOnGround(
				fromIn(liquidEquivalentOfSnow));
	}

}
