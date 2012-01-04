package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.TwentyFourHourMaxMinTemperatureParser;

import static com.berico.ei.ConversionUtils.*;

public class TwentyFourHourMaxMinTemperatureParserTest extends
		EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new TwentyFourHourMaxMinTemperatureParser();
	}
	
	@Test
	public void parser_correctly_identifies_twenty_four_hour_max_min_group() {
		
		assertCanParse("402340123");
		assertCanParse("400301043");
		assertCanParse("410321121");
		assertCannotParse("041234Z");
	}

	public void assertTwentyFourHourMaxMinTemperatures(double expectedMax, double expectedMin, String element){
		
		EncodedWxStringParseContext context = assertParse(element);
		
		assertEquals(expectedMax, 
			toC(context
				.getObservation()
				.getTemperatures()
				.getTwentyFourHourMaximumTemperature()), 0.01d);
		
		assertEquals(expectedMin, 
				toC(context
					.getObservation()
					.getTemperatures()
					.getTwentyFourHourMinimumTemperature()), 0.01d);
	}
	
	@Test
	public void correctly_parses_positive_24_hour_max_min_temperatures(){
		
		assertTwentyFourHourMaxMinTemperatures(12.3d, 1.2d, "401230012");
	}

	@Test
	public void correctly_parses_positive_24_hour_max_and_24_hour_negative_min_temperatures(){
		
		assertTwentyFourHourMaxMinTemperatures(5.2d, -0.5d, "400521005");
	}
	
	@Test
	public void correctly_parses_negative_24_hour_max_and_min_temperatures(){
		
		assertTwentyFourHourMaxMinTemperatures(-4.3d, -11.2d, "410431112");
	}
}
