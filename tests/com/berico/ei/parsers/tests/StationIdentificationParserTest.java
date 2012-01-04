package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.StationIdentificationParser;

public class StationIdentificationParserTest extends
		EncodedWxElementParserBaseTestCase {
	

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new StationIdentificationParser();
	}
	
	@Test
	public void ensure_the_parser_knows_elements_it_can_correctly_parse() {
		
		assertTrue(
				getParser().canParseCurrentElement(
						this.createContext("KNFG METAR 251255Z")));
		
		assertFalse(
				getParser().canParseCurrentElement(
						this.createContext("METAR")));
		
		assertFalse(
				getParser().canParseCurrentElement(
						this.createContext("SPECI")));
	}
	

	@Test
	public void correct_station_is_identified_by_the_encoded_icao_element(){
			
		EncodedWxStringParseContext context = assertParse("KNFG METAR 251255Z");
		
		assertNotNull(context.getObservation().getObservingStation());
		assertEquals("KNFG", context.getObservation().getObservingStation().getIcaoStationIdentifier());
		
	}

	
	
	
}
