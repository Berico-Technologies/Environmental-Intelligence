package com.berico.ei.parsers;

public final class EncodedWxElementPatternMatchers {

	public static final String ICAO_PATTERN = "^[A-Z][A-Z0-9]{3}$";
	
	public static final String DATE_TIME_PATTERN = "([0-2][0-9]|[3][0-1])([0-1][0-9]|[2][0-3])([0-5][0-9])Z";
	
	public static final String PREVAILING_VISIBILITY_METERS_PATTERN = "[0-9]{4}";
	
	public static final String SINGLE_NUMBER_TEST = "[0-9]{1}";
	
	public static final String PREVAILING_VISIBILITY_MILES_PATTERN = "^(([0-9]{1,2})|(([0-2][ ])?[0-9][/][0-9]{1,2}))SM";
	
	public static final String WINDS_PATTERN = "((VRB0[1-6])|(00000)|([0-2][0-9]0|3[0-6]0)[0-9]{2,3}(G[0-9]{2,3})?)KT";
	
	public static final String TEMPERATURE_DEWPOINT_PATTERN = "^([M]?[0-9]{2}[/][M]?[0-9]{2})$";
	
	public static final String TEMPERATURE_DEWPOINT_GROUP_PATTERN = "T[0-1][0-9]{3}[0-1][0-9]{3}";
	
	public static final String MAX_MIN_TEMPERATURE_GROUP_PATTERN = "^[1-2][0-1][0-9]{3}$";
	
	public static final String TWENTY_FOUR_HOUR_MAX_MIN_TEMPERATURE_PATTERN = "4[0-1][0-9]{3}[0-1][0-9]{3}";
	
	public static final String ONE_THREE_SIX_TWENTY_FOUR_HOUR_PRECIP_GROUP_PATTERN = "^[6-7P][0-9]{4}$";
	
	public static final String LIQUID_EQUIVALENT_OF_SNOW_GROUP_PATTERN = "^933[0-9]{3}$";
	
	public static final String SNOW_DEPTH_GROUP_PATTERN = "^4/[0-9]{3}$";
	
	public static final String THREE_HOUR_PRESSURE_TENDENCY_PATTERN = "^5[0-8][0-9]{3}$";
	
	public static final String CLOUD_CLASSIFICATION_GROUP_PATTERN = "^8/[0-9]{3}$";
	
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

		return element.matches(PREVAILING_VISIBILITY_METERS_PATTERN) 
				|| element.matches(PREVAILING_VISIBILITY_MILES_PATTERN) 
				|| isMultiElementPrevailingVisibility(element, nextElement);
	}
	
	public static boolean isMultiElementPrevailingVisibility(String element, String nextElement){
		
		if (element.matches(SINGLE_NUMBER_TEST)){
			String currentAndNextElement = String.format("%s %s", element, nextElement);
			
			if(currentAndNextElement.matches(PREVAILING_VISIBILITY_MILES_PATTERN)){
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean isWindsElement(String element){
		
		return element.matches(WINDS_PATTERN);
	}
	
	public static boolean isTemperatureDewpointElement(String element){
		
		return element.matches(TEMPERATURE_DEWPOINT_PATTERN);
	}
	
	public static boolean isTemperatureDewpointGroupElement(String element){
		
		return element.matches(TEMPERATURE_DEWPOINT_GROUP_PATTERN);
	}
	
	public static boolean isMaxOrMinTemperatureGroup(String element){
		
		return element.matches(MAX_MIN_TEMPERATURE_GROUP_PATTERN);
	}
	
	public static boolean isMaxTemperatureGroup(String element){
		
		return element.startsWith("1") && isMaxOrMinTemperatureGroup(element);
	}
	
	public static boolean isTwentyFourHourMaxMinTemperature(String element){
		
		return element.matches(TWENTY_FOUR_HOUR_MAX_MIN_TEMPERATURE_PATTERN);
	}
	
	public static boolean isPrecipGroup(String element){
		
		return element.matches(ONE_THREE_SIX_TWENTY_FOUR_HOUR_PRECIP_GROUP_PATTERN);
	}
	
	public static boolean isHourlyPrecipGroup(String element){
		
		return element.startsWith("P") && isPrecipGroup(element);
	}
	
	public static boolean isThreeOrSixHourPrecipGroup(String element){
		
		return element.startsWith("6") && isPrecipGroup(element);
	}
	
	public static boolean isTwentyFourHourPrecipGroup(String element){
		
		return element.startsWith("7") && isPrecipGroup(element);
	}
	
	public static boolean isSnowDepthElement(String element){
		
		return element.matches(SNOW_DEPTH_GROUP_PATTERN);
	}
	
	public static boolean isLiquidEquivalentOfSnowElement(String element){
		
		return element.matches(LIQUID_EQUIVALENT_OF_SNOW_GROUP_PATTERN);
	}
	
	public static boolean isMinTemperatureGroup(String element){
		
		return element.startsWith("2") && isMaxOrMinTemperatureGroup(element);
	}
	
	public static boolean isAlimeterElement(String element){
		
		return element.matches(ALTIMETER_PATTERN);
	}
	
	public static boolean isSeaLevelPressureElement(String element){
		
		return element.matches(SLP_PATTERN);
	}
	
	public static boolean isPressureTendencyElement(String element){
		
		return element.matches(THREE_HOUR_PRESSURE_TENDENCY_PATTERN);
	}
	
	public static boolean isCloudLayerElement(String element){
		
		return element.matches(CLOUD_LAYER_PATTERN);
	}
	
	public static boolean isCloudClassificationGroup(String element){
		
		return element.matches(CLOUD_CLASSIFICATION_GROUP_PATTERN);
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
