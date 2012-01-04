package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.berico.ei.CloudEtages;
import com.berico.ei.parsers.CloudClassificationGroupParser;
import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;

public class CloudClassificationGroupParserTest extends
		EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new CloudClassificationGroupParser();
	}
	
	@Test
	public void parser_correctly_identifies_cloud_classification_group() {

		assertCanParse("8/123");
		assertCannotParse("81234");
		assertCannotParse("181212Z");
	}

	public void assertCloudClassificationGroup(int low, int mid, int high, String element){
		
		EncodedWxStringParseContext context = assertParse(element);
		
		assertEquals(
			CloudEtages.Low.fromCode(low), 
			context.getObservation().getSkyCondition().getEtages().getLow());
		
		assertEquals(
			CloudEtages.Mid.fromCode(mid), 
			context.getObservation().getSkyCondition().getEtages().getMid());
		
		assertEquals(
			CloudEtages.High.fromCode(high), 
			context.getObservation().getSkyCondition().getEtages().getHigh());
		
	}
	
	@Test
	public void parser_correctly_extracts_low_mid_and_high_etages_from_group(){
		
		assertCloudClassificationGroup(0, 1, 2, "8/012");
		assertCloudClassificationGroup(1, 2, 3, "8/123");
		assertCloudClassificationGroup(2, 3, 4, "8/234");
		assertCloudClassificationGroup(0, 0, 0, "8/000");
		assertCloudClassificationGroup(0, 8, 0, "8/080");
		assertCloudClassificationGroup(9, 6, 3, "8/963");
		assertCloudClassificationGroup(2, 0, 2, "8/202");
		assertCloudClassificationGroup(0, 1, 9, "8/019");
	}

}
