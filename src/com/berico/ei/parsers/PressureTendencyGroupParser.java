package com.berico.ei.parsers;

import static com.berico.ei.ConversionUtils.*;
import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;
import static com.berico.ei.parsers.ParserUtils.*;

import com.berico.ei.PressureTendency;

public class PressureTendencyGroupParser implements EncodedWxElementParser {

	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isPressureTendencyElement(context.getCurrentElement());
	}

	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		PressureTendency pressureTendency = new PressureTendency();
		
		pressureTendency.setChangeCategory(
				PressureTendency.ChangeCategory
					.fromCode(
						Integer.parseInt(
							context.getCurrentElement().substring(1,2))));
		
		pressureTendency.setMagnitudeOfChange(
				fromMb(
					parseEncodedMbPressure(
						context.getCurrentElement().substring(2))));
		
		context
			.getObservation()
			.getPressures()
			.setPressureTendency(pressureTendency);
	}

}
