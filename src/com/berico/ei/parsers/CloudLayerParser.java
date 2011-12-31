package com.berico.ei.parsers;

import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

import javax.measure.unit.NonSI;

import org.jscience.geography.coordinates.Height;

import com.berico.ei.SkyCondition;
import com.berico.ei.CloudLayer;
import com.berico.ei.SkyCoverage;

public class CloudLayerParser implements EncodedWxElementParser {

	@Override
	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isCloudLayerElement(context.getCurrentElement());
	}

	@Override
	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		if(context.getObservation().getSkyCondition() == null){
			
			context.getObservation().setSkyCondition(new SkyCondition());
		}
		
		String coverageStr = null;
		
		int length = context.getCurrentElement().length();
		
		String heightStr = context.getCurrentElement().substring(length - 3);
		
		if(context.getCurrentElement().startsWith("VV")){
			coverageStr = "VV";
		} 
		else {
			coverageStr = context.getCurrentElement().substring(0, 3);
		}
		
		SkyCoverage coverage = SkyCoverage.fromEncodedString(coverageStr);
		
		if(coverage == null){
			throw new EncodedWxElementParseException(
					String.format("[%s] is not a valid encoded cloud layer value.", coverageStr), this, context);
		}
		
		if(coverage == SkyCoverage.Clear){
			
			context.getObservation().getSkyCondition().setSkyClear();
		}
		else {
		
			int height = Integer.parseInt(heightStr) * 100;
			
			CloudLayer layer = new CloudLayer(coverage, Height.valueOf(height, NonSI.FOOT));
			
			context.getObservation().getSkyCondition().addLayer(layer);
		}
	}

}
