package com.berico.ei.parsers;

import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.jscience.physics.amount.Amount;

import com.berico.ei.Visibility;

public class PrevailingVisibilityParser implements EncodedWxElementParser {


	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		
		return isPrevailingVisibilityElement(context.getCurrentElement(), context.getNextElement());
	}


	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		if(context.getObservation().getVisibility() == null){
			
			context.getObservation().setVisibility(new Visibility());
			
		} 
		
		if(context.getCurrentElement().matches(PREVAILING_VISIBILITY_METERS)){
			
			try {
			
				int visibilityInMeters = Integer.parseInt(context.getCurrentElement());
				
				context.getObservation()
						.getVisibility()
						.setPrevailingVisibility(
						Amount.valueOf(visibilityInMeters, SI.METER));
				
			} catch (Exception ex){
				
				ex.printStackTrace();
			}
		}
		else if (context.getCurrentElement().matches(PREVAILING_VISIBILITY_MILES)){
			
			double visibilityMiles = parseVisibilityInMiles(context.getCurrentElement());
			
			context.getObservation()
				.getVisibility()
				.setPrevailingVisibility(
				Amount.valueOf(visibilityMiles, NonSI.MILE));
		}
		else if(isMultiElementPrevailingVisibility(context.getCurrentElement(), context.getNextElement())){
				
			String potentialVisString = String.format("%s %s", context.getCurrentElement(), context.getNextElement());
			
			double visibilityMiles = parseVisibilityInMiles(potentialVisString);
			
			context.getObservation()
			.getVisibility()
			.setPrevailingVisibility(
			Amount.valueOf(visibilityMiles, NonSI.MILE));
		}		
	}
	
	protected double parseVisibilityInMiles(String element){
		
		String elementWithoutSM = stripSM(element);
		
		if(elementWithoutSM.contains("/")){
			
			int wholeNumber = 0;
			String fractionStr = elementWithoutSM;
			
			if(elementWithoutSM.trim().contains(" ")){
				
				String[] parts = elementWithoutSM.split(" ");
				
				wholeNumber = Integer.parseInt(parts[0]);
				fractionStr = parts[1];
			}

			double fraction = 0;
			
			try {
				
				fraction = parseFraction(fractionStr);
			
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return wholeNumber + fraction;
		}
		else {
		
			return Double.parseDouble(elementWithoutSM.trim());
		}
	}
	
	protected String stripSM(String visibilityElement){
		return visibilityElement.replace("SM", "");
	}
	
	protected double parseFraction(String fraction) throws Exception {
		
		String[] parts = fraction.trim().split("/");
		assert parts.length == 2;
		
		double numerator = Double.parseDouble(parts[0]);
		double denominator = Double.parseDouble(parts[1]);
		
		return numerator / denominator;
	}
	
}
