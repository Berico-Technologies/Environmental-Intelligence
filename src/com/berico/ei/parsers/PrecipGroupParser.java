package com.berico.ei.parsers;

import static com.berico.ei.ConversionUtils.*;
import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;
import static com.berico.ei.parsers.ParserUtils.*;

public class PrecipGroupParser implements EncodedWxElementParser {

	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isPrecipGroup(context.getCurrentElement());
	}

	
	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
	
		double precip = parseEncodedPrecip(context
							.getCurrentElement().substring(1));
		
		if(isHourlyPrecipGroup(context.getCurrentElement())){
			
			context
				.getObservation()
				.getPrecipitation()
				.setHourlyPrecipitation(
					fromIn(precip));
		}
		else if(isThreeOrSixHourPrecipGroup(context.getCurrentElement())){
			
			context
			.getObservation()
			.getPrecipitation()
			.setThreeHourPrecipitation(
				fromIn(precip));
		}
		else if(isTwentyFourHourPrecipGroup(context.getCurrentElement())){
			
			context
			.getObservation()
			.getPrecipitation()
			.setTwentyFourHourPrecipitation(
				fromIn(precip));
		}
		
	}

}
