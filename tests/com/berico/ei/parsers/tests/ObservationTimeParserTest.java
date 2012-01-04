package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.ObservationTimeParser;

public class ObservationTimeParserTest extends
		EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new ObservationTimeParser();
	}

	@Test
	public void ensure_the_parser_knows_elements_it_can_correctly_parse()  {
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
						this.createContext("251223Z")));
		
		//Day of Month Invalid
		assertFalse(
				getParser()
					.canParseCurrentElement(
						this.createContext("321223Z")));
		
		//Hour of Day Invalid
		assertFalse(
				getParser()
					.canParseCurrentElement(
						this.createContext("122423Z")));

		//Minutes of Hour Invalid
		assertFalse(
				getParser()
					.canParseCurrentElement(
						this.createContext("122361Z")));
				
		assertFalse(
				getParser()
					.canParseCurrentElement(
						this.createContext("NON PARSEABLE STRING")));
		
	}
	
	@Test
	public void parser_correctly_extracts_the_time_from_encoded_observation() {
		
		EncodedWxStringParseContext context = assertParse("251223Z");
		
		DateTime dt = context.getObservation().getTimeOfObservation();
		
		assertEquals(23, dt.getMinuteOfHour());
		assertEquals(12, dt.getHourOfDay());
		assertEquals(25, dt.getDayOfMonth());
		assertEquals(this.getObservationMonth(), dt.getMonthOfYear());
		assertEquals(this.getObservationYear(), dt.getYear());
		assertEquals(DateTimeZone.UTC, dt.getZone());
		
	}

}
