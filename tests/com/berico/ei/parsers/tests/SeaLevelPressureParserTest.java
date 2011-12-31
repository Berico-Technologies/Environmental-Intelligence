package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParseException;
import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.SeaLevelPressureParser;

public class SeaLevelPressureParserTest extends
		EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new SeaLevelPressureParser();
	}

	@Test
	public void parser_correctly_identifies_an_encoded_slp_element() {
		
		assertTrue(
			getParser()
				.canParseCurrentElement(
					createContext("SLP092")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
						createContext("SLP892")));
		
		assertFalse(
				getParser()
					.canParseCurrentElement(
						createContext("SLPX")));
	}

	
	public void assertSeaLevelPressure(double expectedSlp, String encodedSlp){
		
		EncodedWxStringParseContext context = createContext(encodedSlp);
		
		try {
			
			getParser().performParse(context);
			
		} catch (EncodedWxElementParseException e) {

			fail(e.getMessage());
		}
		
		assertEquals(expectedSlp, 
				context.getObservation()
					.getSeaLevelPressure()
					.doubleValue(
						SI.MILLI(NonSI.BAR)), 0.1d);
	}
	
	@Test
	public void parser_correctly_extracts_slp_over_1000_mb(){
		
		assertSeaLevelPressure(1019.1d, "SLP191");
	}
	
	@Test
	public void parser_correctly_extracts_slp_under_1000_mb(){
		
		assertSeaLevelPressure(990.3d, "SLP903");
	}

}
