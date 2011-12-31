package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import javax.measure.unit.NonSI;

import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParseException;
import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.WindsParser;

public class WindsParserTest extends EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new WindsParser();
	}

	@Test
	public void ensure_the_parser_knows_elements_it_can_correctly_parse(){
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("21010KT")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("VRB06KT")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("21010G20KT")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("210100KT")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("21010G100KT")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
							this.createContext("210110G130KT")));
		
		assertFalse(
				getParser()
					.canParseCurrentElement(
							this.createContext("VRB10KT")));
		
		assertFalse(
				getParser()
					.canParseCurrentElement(
							this.createContext("VRB07KT")));
		
		assertFalse(
				getParser()
					.canParseCurrentElement(
							this.createContext("METAR")));
	}

	
	public void assertWindConditions(int expectedDirection, int expectedSpeed, int expectedGusts, boolean shouldBeVariable, String encodedWindString){
		
		EncodedWxStringParseContext context = this.createContext(encodedWindString);
		
		try {
			
			getParser().performParse(context);
		
		} catch (EncodedWxElementParseException e) {
			
			fail(e.getMessage());
		}
		
		if(shouldBeVariable){
			
			assertTrue(context.getObservation().getWinds().isVariable());
			
		} else {
			
			assertFalse(context.getObservation().getWinds().isVariable());
			
			assertEquals(expectedDirection, 
					context
						.getObservation()
						.getWinds()
						.getWindDirection()
						.longValue(NonSI.DEGREE_ANGLE));
		}
		
		assertEquals(expectedSpeed,
				context
					.getObservation()
					.getWinds()
					.getWindSpeed()
					.longValue(NonSI.KNOT));
		
		if(expectedGusts <= 0){
			
			assertFalse(context.getObservation().getWinds().hasGusts());
			
		} else {
			
			assertTrue(context.getObservation().getWinds().hasGusts());
			
			assertEquals(expectedGusts,
					context
						.getObservation()
						.getWinds()
						.getWindGust()
						.longValue(NonSI.KNOT));
		}
	}
	
	
	@Test
	public void variable_winds_are_parsed_correctly(){
		
		assertWindConditions(0, 5, 0, true, "VRB05KT");
	}
	
	@Test
	public void noncomplex_wind_string_is_parsed_correctly(){
		
		assertWindConditions(210, 10, 0, false, "21010KT");
	}

	@Test
	public void nongusting_wind_over_100_knots_is_parsed_correctly(){
		
		assertWindConditions(90, 101, 0, false, "090101KT");
	}
	
	@Test
	public void gusting_wind_is_parsed_correctly(){
		
		assertWindConditions(350, 10, 15, false, "35010G15KT");
	}
	
	@Test
	public void gusting_wind_over_100_knots_is_parsed_correctly(){
 
		assertWindConditions(110, 90, 150, false, "11090G150KT");
	}
	
	@Test
	public void sustained_wind_over_100_knots_and_gusting_wind_over_100_knots_is_parsed_correctly(){
		
		assertWindConditions(60, 100, 150, false, "060100G150KT");
	}
}