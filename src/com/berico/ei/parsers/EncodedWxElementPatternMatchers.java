package com.berico.ei.parsers;

public final class EncodedWxElementPatternMatchers {

	public static final String ICAO_PATTERN = "^[A-Z][A-Z0-9]{3}$";
	
	public static final String DATE_TIME_PATTERN = "([0-2][0-9]|[3][0-1])([0-1][0-9]|[2][0-3])([0-5][0-9])Z";
	
	public static final String PREVAILING_VISIBILITY_METERS = "[0-9]{4}";
	
	public static final String SINGLE_NUMBER_TEST = "[0-9]{1}";
	
	public static final String PREVAILING_VISIBILITY_MILES = "^(([0-9]{1,2})|(([0-2][ ])?[0-9][/][0-9]{1,2}))SM";
	
	public static final String WINDS_PATTERN = "((VRB0[1-6])|(00000)|([0-2][0-9]0|3[0-6]0)[0-9]{2,3}(G[0-9]{2,3})?)KT";
	
	public static final String TEMPERATURE_DEW_POINT_PATTERN = "^([M]?[0-9]{2}[/][M]?[0-9]{2})$";
	
	public static final String ALTIMETER_PATTERN = "A[0-9]{4}";
	
	public static final String SLP_PATTERN = "SLP[0-9]{3}";
	
	public static final String CLOUD_LAYER_PATTERN = "SKC|CLR|VV00[0-9]|((FEW|SCT|BKN|OVC)[0-5][0-9]{2})";
	
	public static boolean isPotentialIcaoElement(String element){
		
		return element.matches(ICAO_PATTERN);
	}
	
	public static boolean isDateTimeElement(String element){
		
		return element.matches(DATE_TIME_PATTERN);
	}
	
	public static boolean isObservationTypeElement(String element){
		
		return element.equals("METAR") || element.equals("SPECI");
	}
	
	public static boolean isPrevailingVisibilityElement(String element, String nextElement){

		return element.matches(PREVAILING_VISIBILITY_METERS) 
				|| element.matches(PREVAILING_VISIBILITY_MILES) 
				|| isMultiElementPrevailingVisibility(element, nextElement);
	}
	
	public static boolean isMultiElementPrevailingVisibility(String element, String nextElement){
		
		if (element.matches(SINGLE_NUMBER_TEST)){
			String currentAndNextElement = String.format("%s %s", element, nextElement);
			
			if(currentAndNextElement.matches(PREVAILING_VISIBILITY_MILES)){
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean isWindsElement(String element){
		
		return element.matches(WINDS_PATTERN);
	}
	
	public static boolean isTemperatureDewPointElement(String element){
		
		return element.matches(TEMPERATURE_DEW_POINT_PATTERN);
	}
	
	public static boolean isAlimeterElement(String element){
		
		return element.matches(ALTIMETER_PATTERN);
	}
	
	public static boolean isSeaLevelPressureElement(String element){
		
		return element.matches(SLP_PATTERN);
	}
	
	public static boolean isCloudLayerElement(String element){
		
		return element.matches(CLOUD_LAYER_PATTERN);
	}
	
	
	public static boolean isAfterCurrentElement(int currentElement, String[] elements, Predicate afterElementMatcher){
		
		assert currentElement >= 0 && currentElement < elements.length;
		
		for(int i = (currentElement + 1); i < elements.length; i++){
			if(afterElementMatcher.isMatch(elements[i])){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isBeforeCurrentElement(int currentElement, String[] elements, Predicate beforeElementMatcher){
	
		assert currentElement <= elements.length && currentElement > 0;
		
		for(int i = (currentElement - 1); i >= 0; i--){
			if(beforeElementMatcher.isMatch(elements[i])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * A simple contract for a Predicate against a string
	 */
	public interface Predicate {
		
		boolean isMatch(String testElement);
		
	}
	
	
}
