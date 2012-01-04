package com.berico.ei.parsers;

import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

import com.berico.ei.CloudEtages;

public class CloudClassificationGroupParser implements EncodedWxElementParser {

	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isCloudClassificationGroup(context.getCurrentElement());
	}

	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		CloudEtages.Low low = CloudEtages.Low
								.fromCode(
									Integer.parseInt(
										context.getCurrentElement().substring(2, 3)));
		
		context
			.getObservation()
			.getSkyCondition()
			.getEtages()
			.setLow(low);
		
		CloudEtages.Mid mid = CloudEtages.Mid
				.fromCode(
					Integer.parseInt(
						context.getCurrentElement().substring(3, 4)));
		
		context
			.getObservation()
			.getSkyCondition()
			.getEtages()
			.setMid(mid);
			
		CloudEtages.High high = CloudEtages.High
				.fromCode(
					Integer.parseInt(
						context.getCurrentElement().substring(4)));
		
		context
			.getObservation()
			.getSkyCondition()
			.getEtages()
			.setHigh(high);
	}

}
