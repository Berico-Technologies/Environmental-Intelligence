package com.berico.ei.parsers;

import com.berico.ei.Observation.ObservationTypes;
import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

public class ObservationTypeParser implements EncodedWxElementParser {


	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		return isObservationTypeElement(context.getCurrentElement());
	}


	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		if(context.getCurrentElement().equals("METAR")){
			context.getObservation().setObservationType(ObservationTypes.METAR);
		}
		
		if(context.getCurrentElement().equals("SPECI")){
			context.getObservation().setObservationType(ObservationTypes.SPECI);
		}
	}

}
