package com.berico.ei.parsers;

import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.jscience.physics.amount.Amount;

public class SeaLevelPressureParser implements EncodedWxElementParser {

	public static double SLP_CUTOVER_THRESHOLD = 49.9;
	

	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isSeaLevelPressureElement(context.getCurrentElement());
	}


	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		double slpFullValue = Double.MAX_VALUE;
		
		double slpPartial = Integer.parseInt(context.getCurrentElement().substring(3)) * 0.1d;
		
		if(slpPartial > SLP_CUTOVER_THRESHOLD){
			
			slpFullValue = slpPartial + 900;
		} 
		else {
			
			slpFullValue = slpPartial + 1000;
		}
		
		context.getObservation().getPressures().setSeaLevelPressure(Amount.valueOf(slpFullValue, SI.MILLI(NonSI.BAR)));
	}

}
