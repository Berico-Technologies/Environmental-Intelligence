package com.berico.ei.parsers.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.measure.unit.NonSI;

import org.jscience.geography.coordinates.Height;
import org.junit.Test;

import com.berico.ei.CloudLayer;
import com.berico.ei.SkyCoverage;
import com.berico.ei.parsers.CloudLayerParser;
import com.berico.ei.parsers.EncodedWxElementParseException;
import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;
import com.berico.ei.parsers.EncodedWxStringParseContext.ContextHandler;

public class CloudLayerParserTest extends EncodedWxElementParserBaseTestCase {

	@Override
	protected EncodedWxElementParser createParserInstance() {
		
		return new CloudLayerParser();
	}
	
	@Test
	public void parser_correctly_identified_cloud_layer_elements() {
		
		assertTrue(
			getParser()
				.canParseCurrentElement(
					createContext("SKC")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
						createContext("CLR")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
						createContext("FEW010")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
						createContext("SCT002")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
						createContext("BKN200")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
						createContext("OVC120")));
		
		assertTrue(
				getParser()
					.canParseCurrentElement(
						createContext("VV001")));
		
		assertFalse(
				getParser()
					.canParseCurrentElement(
						createContext("VV010")));
		
		assertFalse(
				getParser()
					.canParseCurrentElement(
						createContext("VV100")));
		
		assertFalse(
				getParser()
					.canParseCurrentElement(
						createContext("VV010")));
		
		assertFalse(
				getParser()
					.canParseCurrentElement(
						createContext("FEWXXX")));
		
		assertFalse(
				getParser()
					.canParseCurrentElement(
						createContext("METAR")));
	}

	
	public void assertSkyCondition(
			Collection<CloudLayer> expectedLayers, 
			CloudLayer expectedCeiling, 
			boolean shouldBeClear, 
			String encodedWeatherString){
		
		EncodedWxStringParseContext context = createContext(encodedWeatherString);
		
		context.foreachElement(new ContextHandler() {
			
			public void handleElement(EncodedWxStringParseContext context) {
				
				if(getParser().canParseCurrentElement(context)){
					
					try {
						
						getParser().performParse(context);
						
					} catch (EncodedWxElementParseException e) {
						
						fail(e.getMessage());
					}
				}
			}
		});
		
		assertEquals(shouldBeClear, context.getObservation().getSkyCondition().isClear());
		
		if(expectedCeiling != null){
			
			assertTrue(context.getObservation().getSkyCondition().hasCeiling());
			
			assertEquals(expectedCeiling, context.getObservation().getSkyCondition().getCeiling());
		}
		
		assertEquals(expectedLayers.size(), context.getObservation().getSkyCondition().getLayers().size());
		assertTrue(context.getObservation().getSkyCondition().getLayers().containsAll(expectedLayers));
	}

	protected List<CloudLayer> createList(CloudLayer... layers){
		List<CloudLayer> layersList = new ArrayList<CloudLayer>();
		Collections.addAll(layersList, layers);
		return layersList;
	}
	
	@Test
	public void sky_clear_correctly_parsed(){
		
		assertSkyCondition(Collections.<CloudLayer> emptyList(), null, true, "SKC");
	}
	
	@Test
	public void clear_correctly_parsed(){
		
		assertSkyCondition(Collections.<CloudLayer> emptyList(), null, true, "CLR");
	}
	
	@Test
	public void single_element_sky_condition_correctly_parsed_without_a_ceiling(){
		
		List<CloudLayer> expectedLayers = createList(
				new CloudLayer(SkyCoverage.Few, Height.valueOf(10000, NonSI.FOOT)));
		
		assertSkyCondition(expectedLayers, null, false, "FEW100");
	}
	
	@Test
	public void two_element_sky_condition_correctly_parsed_without_a_ceiling(){
		
		List<CloudLayer> expectedLayers = createList(
				new CloudLayer(SkyCoverage.Few, Height.valueOf(10000, NonSI.FOOT)),
				new CloudLayer(SkyCoverage.Scattered, Height.valueOf(12000, NonSI.FOOT)));
		
		assertSkyCondition(expectedLayers, null, false, "FEW100 SCT120");
		
	}
	
	@Test
	public void single_element_sky_condition_correctly_parsed_with_a_ceiling(){
	
		List<CloudLayer> expectedLayers = createList(
				new CloudLayer(SkyCoverage.Broken, Height.valueOf(10000, NonSI.FOOT)));
		
		assertSkyCondition(expectedLayers, expectedLayers.get(0), false, "BKN100");
	}
	
	@Test
	public void two_element_sky_condition_correctly_parsed_with_a_ceiling(){
	
		List<CloudLayer> expectedLayers = createList(
				new CloudLayer(SkyCoverage.Broken, Height.valueOf(5000, NonSI.FOOT)),
				new CloudLayer(SkyCoverage.Broken, Height.valueOf(10000, NonSI.FOOT)));
		
		assertSkyCondition(expectedLayers, expectedLayers.get(0), false, "BKN050 BKN100");
	}
	
	@Test
	public void three_element_sky_condition_correctly_parsed_with_a_ceiling(){
	
		List<CloudLayer> expectedLayers = createList(
				new CloudLayer(SkyCoverage.Few, Height.valueOf(5000, NonSI.FOOT)),
				new CloudLayer(SkyCoverage.Scattered, Height.valueOf(7000, NonSI.FOOT)),
				new CloudLayer(SkyCoverage.Broken, Height.valueOf(10000, NonSI.FOOT)));
		
		assertSkyCondition(expectedLayers, expectedLayers.get(2), false, "FEW050 SCT070 BKN100");
	}
	
	@Test
	public void five_element_sky_condition_correctly_parsed_with_a_ceiling(){
	
		List<CloudLayer> expectedLayers = createList(
				new CloudLayer(SkyCoverage.Few, Height.valueOf(2000, NonSI.FOOT)),
				new CloudLayer(SkyCoverage.Few, Height.valueOf(5000, NonSI.FOOT)),
				new CloudLayer(SkyCoverage.Scattered, Height.valueOf(7000, NonSI.FOOT)),
				new CloudLayer(SkyCoverage.Broken, Height.valueOf(10000, NonSI.FOOT)),
				new CloudLayer(SkyCoverage.Overcast, Height.valueOf(12000, NonSI.FOOT)));
		
		assertSkyCondition(expectedLayers, expectedLayers.get(3), false, "FEW020 FEW050 SCT070 BKN100 OVC120");
	}
	
	@Test
	public void single_element_vertical_visibility_correctly_parsed(){
		
		List<CloudLayer> expectedLayers = createList(
				new CloudLayer(SkyCoverage.VerticalVisibility, Height.valueOf(100, NonSI.FOOT)));
		
		assertSkyCondition(expectedLayers, expectedLayers.get(0), false, "VV001");
		
	}
	
	@Test
	public void two_cloud_layer_elements_with_vertical_visibility_correctly_parsed(){
		
		List<CloudLayer> expectedLayers = createList(
				new CloudLayer(SkyCoverage.Scattered, Height.valueOf(100, NonSI.FOOT)),
				new CloudLayer(SkyCoverage.VerticalVisibility, Height.valueOf(500, NonSI.FOOT)));
		
		assertSkyCondition(expectedLayers, expectedLayers.get(1), false, "SCT001 VV005");
		
	}
	
}
