package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import javax.measure.unit.NonSI;

import org.junit.Test;

import com.berico.ei.parsers.AltimeterParser;
import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;

public class AltimeterParserTest extends EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new AltimeterParser();
	}
	
	@Test
	public void parser_properly_recognizes_an_altimeter_element() {
		
		assertCanParse("A2992");	
		assertCanParse("A3001");
		assertCannotParse("METAR");
	}

	public void assertAltimeter(double expected, String encodedAltimeter){
		
		EncodedWxStringParseContext context = assertParse(encodedAltimeter);
		
		assertEquals(expected, 
			context
				.getObservation()
				.getPressures()
				.getAltimeter()
				.doubleValue(NonSI.INCH_OF_MERCURY), 0.01d);
	}
	
	@Test
	public void properly_parse_altimeter_under_30_inches_from_an_encoded_element(){
		
		assertAltimeter(29.92, "A2992");
	}
	
	@Test
	public void properly_parse_altimeter_over_30_inches_from_an_encoded_element(){
		
		assertAltimeter(30.01, "A3001");
	}

	
	
	
}
