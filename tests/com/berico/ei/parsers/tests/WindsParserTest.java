package com.berico.ei.parsers.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.measure.unit.NonSI;

import org.junit.Test;

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
		
		assertCanParse("21010KT");
		assertCanParse("VRB06KT");
		assertCanParse("21010G20KT");
		assertCanParse("210100KT");
		assertCanParse("21010G100KT");
		assertCanParse("210110G130KT");
		assertCannotParse("VRB10KT");
		assertCannotParse("VRB07KT");
		assertCannotParse("METAR");
	}

	
	public void assertWindConditions(int expectedDirection, int expectedSpeed, int expectedGusts, boolean shouldBeVariable, String encodedWindString){
		
		EncodedWxStringParseContext context = assertParse(encodedWindString);
		
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
