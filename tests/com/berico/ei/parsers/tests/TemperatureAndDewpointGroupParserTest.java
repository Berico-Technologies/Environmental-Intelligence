package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.TemperatureAndDewpointGroupParser;

import static com.berico.ei.ConversionUtils.*;

public class TemperatureAndDewpointGroupParserTest extends
		EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new TemperatureAndDewpointGroupParser();
	}
	
	@Test
	public void parser_correctly_identifies_temp_and_dewpoint_groups_it_can_parse() {
		
		assertCanParse("T01250020");
		assertCanParse("T01251020");
		assertCanParse("T10101020");
		assertCannotParse("TSRA");
		assertCannotParse("T22221111");
	}

	public void assertTemperatureAndDewpoint(double expectedTemperature, double expectedDewpoint, String element){
		
		EncodedWxStringParseContext context = assertParse(element);
		
		assertEquals(expectedTemperature, 
			toC(context
				.getObservation()
				.getTemperatures()
				.getAmbientAirTemperature()), 0.01d);
		
		assertEquals(expectedDewpoint, 
				toC(context
					.getObservation()
					.getTemperatures()
					.getDewpoint()), 0.01d);
	}

	@Test
	public void correctly_parses_positive_temp_and_dewpoint_group(){
		
		assertTemperatureAndDewpoint(12.5d, 1.2d, "T01250012");
	}
	
	@Test
	public void correctly_parses_positive_temp_and_negative_dewpoint_group(){
		
		assertTemperatureAndDewpoint(5.1d, -2.9d, "T00511029");
	}
	
	@Test
	public void correctly_parses_negative_temp_and_dewpoint_group(){
		
		assertTemperatureAndDewpoint(-5.6d, -13.2d, "T10561132");
	}
	
}
