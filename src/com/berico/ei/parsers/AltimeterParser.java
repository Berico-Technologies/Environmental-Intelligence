package com.berico.ei.parsers;

import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

import javax.measure.unit.NonSI;

import org.jscience.physics.amount.Amount;

public class AltimeterParser implements EncodedWxElementParser {

	@Override
	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isAlimeterElement(context.getCurrentElement());
	}

	@Override
	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {

		int parsedAltimeter = Integer.parseInt(context.getCurrentElement().substring(1));
		
		double altimeter = parsedAltimeter * 0.01;
		
		context.getObservation().setAltimeter(Amount.valueOf(altimeter, NonSI.INCH_OF_MERCURY));
		
	}

}
