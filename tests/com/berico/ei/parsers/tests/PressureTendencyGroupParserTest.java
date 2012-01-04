package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.berico.ei.ConversionUtils.*;

import com.berico.ei.PressureTendency.ChangeCategory;
import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.PressureTendencyGroupParser;

public class PressureTendencyGroupParserTest extends
		EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new PressureTendencyGroupParser();
	}
	
	@Test
	public void parser_correctly_identifies_the_three_hour_pressure_tendency_group() {
		
		assertCanParse("50120");
		assertCanParse("54000");
		assertCanParse("58005");
		assertCannotParse("59001");
		assertCannotParse("050120Z");
	}

	public void assertPressureTendencyGroup(int changeCat, double magnitude, String element){
		
		EncodedWxStringParseContext context = assertParse(element);
		
		assertEquals(ChangeCategory.fromCode(changeCat), 
				context.getObservation().getPressures().getPressureTendency().getChangeCategory());
		
		assertEquals(magnitude, 
				toMb(context.getObservation().getPressures().getPressureTendency().getMagnitudeOfChange()), 0.01d);
	}

	@Test
	public void parser_correctly_extracts_pressure_change_category_and_magnitude_from_element(){
		
		assertPressureTendencyGroup(0, 12.1d, "50121");
		assertPressureTendencyGroup(1, 5.2d, "51052");
		assertPressureTendencyGroup(2, 9.4d, "52094");
		assertPressureTendencyGroup(3, 34.6d, "53346");
		assertPressureTendencyGroup(4, 0.0d, "54000");
		assertPressureTendencyGroup(5, 34.2d, "55342");
		assertPressureTendencyGroup(6, 15.3d, "56153");
		assertPressureTendencyGroup(7, 82.1d, "57821");
		assertPressureTendencyGroup(8, 99.9d, "58999");
	}
}
