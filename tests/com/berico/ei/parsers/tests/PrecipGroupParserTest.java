package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.PrecipGroupParser;

import static com.berico.ei.ConversionUtils.*;

public class PrecipGroupParserTest extends EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new PrecipGroupParser();
	}
	
	@Test
	public void parser_correctly_identifies_hourly_precip_group(){
		
		assertCanParse("P0123");
	}

	@Test
	public void parser_correctly_identifies_three_and_six_hour_precip_group(){
		
		assertCanParse("60001");
	}
	
	@Test
	public void parser_correctly_identifies_twenty_four_hour_precip_group(){
		
		assertCanParse("70430");
	}
	
	@Test 
	public void parser_ignores_incorrect_groups(){
		
		assertCannotParse("SLP0123");
		assertCannotParse("121901Z");
		assertCannotParse("4/123");
	}
	
	public void assertPrecip(double expectedPrecip, int hourlyType, String element){
		
		EncodedWxStringParseContext context = assertParse(element);
		
		switch(hourlyType){
			case 36:
				assertEquals(expectedPrecip, 
						toIn(context
							.getObservation()
							.getPrecipitation()
							.getThreeOrSixHourPrecipitation()), 0.001d);
				break;
			case 24:
				assertEquals(expectedPrecip, 
						toIn(context
							.getObservation()
							.getPrecipitation()
							.getTwentyFourHourPrecipitation()), 0.001d);
				break;
			default: 
				assertEquals(expectedPrecip, 
						toIn(context
							.getObservation()
							.getPrecipitation()
							.getHourlyPrecipitation()), 0.001d);
				break;
		}
	}

	@Test
	public void correctly_parse_hourly_precip(){
		
		assertPrecip(0.31d, 1, "P0031");
		assertPrecip(5.22d, 1, "P0522");
		assertPrecip(12.1d, 1, "P1210");
	}
	
	@Test
	public void correctly_parse_three_and_six_hour_precip(){
		
		assertPrecip(0.31d, 36, "60031");
		assertPrecip(5.22d, 36, "60522");
		assertPrecip(12.1d, 36, "61210");
	}
	
	@Test
	public void correctly_parse_twenty_four_hour_precip(){
		
		assertPrecip(0.31d, 24, "70031");
		assertPrecip(5.22d, 24, "70522");
		assertPrecip(12.1d, 24, "71210");
	}
	
}
