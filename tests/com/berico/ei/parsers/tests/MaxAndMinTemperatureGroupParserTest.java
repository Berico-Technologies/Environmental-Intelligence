package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.ThreeSixHourMaxMinTemperatureParser;

import static com.berico.ei.ConversionUtils.*;

public class MaxAndMinTemperatureGroupParserTest extends
		EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new ThreeSixHourMaxMinTemperatureParser();
	}
	
	@Test
	public void parser_correctly_detects_maximum_temperature_groups() {
		
		assertCanParse("10235");
		assertCanParse("11012");
		// Invalid number in the second position of the string
		assertCannotParse("12235");
		// See if it trips up on a somewhat similar date/time group
		assertCannotParse("110322Z");
	}

	@Test
	public void parser_correctly_detects_minimum_temperature_groups() {
		
		assertCanParse("20102");
		assertCanParse("21045");
		// Invalid number in the second position of the string
		assertCannotParse("22235");	
		// See if it trips up on a somewhat similar date/time group
		assertCannotParse("210322Z");
	}
	
	public void assertMaxMinTemperatureGroup(double expectedTemperature, boolean isMaxTemperature, String element){
		
		EncodedWxStringParseContext context = assertParse(element);
		
		if(isMaxTemperature){
			
			assertNull(context.getObservation().getTemperatures().getThreeOrSixHourMinimumTemperature());
			
			assertEquals(expectedTemperature, 
					toC(context.getObservation().getTemperatures().getThreeOrSixHourMaximumTemperature()), 0.01d);
			
		} else {
			
			assertNull(context.getObservation().getTemperatures().getThreeOrSixHourMaximumTemperature());
			
			assertEquals(expectedTemperature, 
					toC(context.getObservation().getTemperatures().getThreeOrSixHourMinimumTemperature()), 0.01d);
		}
	}

	
	@Test
	public void positive_maximum_temperature_correctly_parsed(){
		
		assertMaxMinTemperatureGroup(10.5d, true, "10105");
	}
	
	@Test
	public void negative_maximum_temperature_correctly_parsed(){
		
		assertMaxMinTemperatureGroup(-0.5d, true, "11005");
	}
	
	@Test
	public void positive_minimum_temperature_correctly_parsed(){
		
		assertMaxMinTemperatureGroup(5.6d, true, "10056");
	}
	
	@Test
	public void negative_minimum_temperature_correctly_parsed(){
		
		assertMaxMinTemperatureGroup(-1.2d, true, "11012");
	}
}
