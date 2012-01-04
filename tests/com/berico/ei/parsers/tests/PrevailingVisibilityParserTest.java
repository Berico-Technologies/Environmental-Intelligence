package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import javax.measure.quantity.Length;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.PrevailingVisibilityParser;

public class PrevailingVisibilityParserTest extends
		EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new PrevailingVisibilityParser();
	}

	@Test
	public void parser_correctly_detects_visibility_elements_in_an_encoded_string() {
		
		assertTrue(
			getParser()
				.canParseCurrentElement(
						this.createContext("10SM")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("7SM")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("9999")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("5000")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("0010")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("2 1/2SM")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("5/8SM")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("1/16SM")));
	}

	public void assertVisibilityConditions(
			String visString, double expectedDistance, Unit<Length> measurement){
		
		EncodedWxStringParseContext context = assertParse(visString);
		
		assertEquals(expectedDistance, 
				context.getObservation()
					.getVisibility()
					.getPrevailingVisibility()
					.doubleValue(measurement), 0d);
	}
	
	@Test
	public void correctly_parse_double_digit_visibility_in_statute_miles(){
		
		assertVisibilityConditions("10SM", 10d, NonSI.MILE);
	}
	
	@Test
	public void correctly_parse_single_digit_visibility_in_statute_miles(){
		
		assertVisibilityConditions("5SM", 5d, NonSI.MILE);
	}
	
	@Test
	public void correctly_parse_single_digit_and_fraction_visibility_in_statute_miles(){
		
		assertVisibilityConditions("1 1/2SM", 1.5d, NonSI.MILE);
	}
	
	@Test
	public void correctly_parse_fraction_visibility_in_statute_miles(){
		
		assertVisibilityConditions("1/2SM", 0.5d, NonSI.MILE);
	}
	
	@Test
	public void correctly_parse_fraction_visibility_with_two_digit_denominator_in_statute_miles(){
		
		double expectedVisibility = 1d / 16d;
		assertVisibilityConditions("1/16SM", expectedVisibility, NonSI.MILE);
	}
	
	@Test
	public void correctly_parse_visibility_in_meters_with_no_leading_zeros(){
		
		assertVisibilityConditions("9000", 9000, SI.METER);
	}

	@Test
	public void correctly_parse_visibility_in_meters_with_leading_zeros(){
		
		assertVisibilityConditions("0030", 30, SI.METER);
	}

	
}
