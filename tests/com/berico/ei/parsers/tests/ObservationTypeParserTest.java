package com.berico.ei.parsers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.berico.ei.Observation.ObservationTypes;
import com.berico.ei.parsers.EncodedWxElementParseException;
import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.ObservationTypeParser;

public class ObservationTypeParserTest extends EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new ObservationTypeParser();
	}
	
	private static String UNPARSEABLE_METAR = "KNFG 242154Z AUTO 21015G12KT 10SM CLR 22/01 A3025 RMK AO2 SLP243 T02220006 TSNO $";
	
	private static String PARSEABLE_METAR = "METAR KNFG 242154Z 21015G12KT 10SM CLR 22/01 A3025 RMK AO2 SLP243 T02220006 TSNO $";
	
	private static String PARSEABLE_SPECI = "SPECI KNFG 242121Z 21015G12KT 10SM CLR 22/01 A3025 RMK AO2 SLP243 TSNO $";
	
	
	@Test
	public void ensure_the_parser_knows_elements_it_can_correctly_parse() {
		
		assertCanParse(PARSEABLE_METAR);
		assertCanParse(PARSEABLE_SPECI);
		assertCannotParse(UNPARSEABLE_METAR);
	}

	public void assertObservationType(ObservationTypes expected, String observationToTest){
	
		EncodedWxStringParseContext metar = this.createContext(observationToTest);
		
		//The default is METAR, so we will set it to SPECI to ensure it is parsed
		//correctly.
		if(expected == ObservationTypes.METAR){
			metar.getObservation().setObservationType(ObservationTypes.SPECI);
		}
		
		try {
			
			getParser().performParse(metar);
		
		} catch (EncodedWxElementParseException e) {

			fail(e.getMessage());
		}
		
		assertEquals(expected, metar.getObservation().getObservationType());
	}
	
	@Test
	public void parser_correctly_extracts_metar_from_encoded_observation() {
		
		assertObservationType(ObservationTypes.METAR, PARSEABLE_METAR);
	}

	@Test
	public void parser_correctly_extracts_speci_from_encoded_observation() {
		
		assertObservationType(ObservationTypes.SPECI, PARSEABLE_SPECI);
	}

	

}
