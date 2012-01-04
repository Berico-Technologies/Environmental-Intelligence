package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.SnowDepthGroupParser;

import static com.berico.ei.ConversionUtils.*;

public class SnowDepthGroupParserTest extends
		EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new SnowDepthGroupParser();
	}
	
	@Test
	public void parser_correctly_identifies_snow_depth_group() {
		
		assertCanParse("4/123");
		assertCannotParse("41123");
		assertCannotParse("124/123");
	}

	public void assertSnowDepthGroup(double expectedDepth, String element){
		
		EncodedWxStringParseContext context = assertParse(element);
		
		assertEquals(expectedDepth, 
			toIn(context.getObservation().getPrecipitation().getSnowDepthOnGround()), 0.01d);	
	}
	
	@Test
	public void parse_correctly_extracts_snow_depth_from_encoded_elements(){
		
		assertSnowDepthGroup(12.3d, "4/123");
		assertSnowDepthGroup(42.0d, "4/420");
		assertSnowDepthGroup(0.5d, "4/005");
	}

}
