package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.LiquidEquivalentOfSnowGroupParser;

import static com.berico.ei.ConversionUtils.*;

public class LiquidEquivalentOfSnowGroupParserTest extends
		EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new LiquidEquivalentOfSnowGroupParser();
	}
	
	@Test
	public void parser_correctly_identifies_the_liquid_equivalent_of_snow_group() {
		
		assertCanParse("933123");
		assertCanParse("933333");
		assertCannotParse("093312Z");
		assertCannotParse("T09330121");
	}

	public void assertLiquidEquivalentOfSnowGroup(double expectedAmount, String element){
		
		EncodedWxStringParseContext context = assertParse(element);
		
		assertEquals(expectedAmount, 
			toIn(context.getObservation().getPrecipitation().getLiquidEquivalentOfSnowOnGround()), 0.01d);
	}
	
	@Test
	public void parser_correctly_extracts_liquid_equivalent_of_snow_magnitude(){
		
		assertLiquidEquivalentOfSnowGroup(11.2d, "933112");
		assertLiquidEquivalentOfSnowGroup(22.5d, "933225");
		assertLiquidEquivalentOfSnowGroup(10.0d, "933100");
		assertLiquidEquivalentOfSnowGroup(0.1d, "933001");
	}

}
