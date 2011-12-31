package com.berico.ei.parsers.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.measure.unit.NonSI;

import org.junit.Test;

import com.berico.ei.parsers.AltimeterParser;
import com.berico.ei.parsers.EncodedWxElementParseException;
import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;

public class AltimeterParserTest extends EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new AltimeterParser();
	}
	
	@Test
	public void parser_properly_recognizes_an_altimeter_element() {
		
		assertTrue(
			getParser()
				.canParseCurrentElement(
					this.createContext("A2992")));
		
		assertTrue(
			getParser()
				.canParseCurrentElement(
					this.createContext("A3001")));
		
		assertFalse(
			getParser()
				.canParseCurrentElement(
					this.createContext("METAR")));
	}

	public void assertAltimeter(double expected, String encodedAltimeter){
		
		EncodedWxStringParseContext context = new EncodedWxStringParseContext(encodedAltimeter);
		
		try {
			
			getParser().performParse(context);
			
		} catch (EncodedWxElementParseException e) {

			fail(e.getMessage());
		}
		
		assertEquals(expected, 
			context
				.getObservation()
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
