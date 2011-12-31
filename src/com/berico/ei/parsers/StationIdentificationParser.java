package com.berico.ei.parsers;

import static com.berico.ei.parsers.EncodedWxElementPatternMatchers.*;

import java.util.HashMap;
import java.util.Map;

import javax.measure.unit.NonSI;

import org.jscience.geography.coordinates.Height;
import org.jscience.geography.coordinates.LatLong;

import com.berico.ei.Station;

public class StationIdentificationParser implements EncodedWxElementParser {


	public static final Map<String, Station> icaoToStationMap = createStationMap();

	@Override
	public void performParse(EncodedWxStringParseContext context)
			throws EncodedWxElementParseException {
		
		if(icaoToStationMap.containsKey(context.getCurrentElement())){
			
			context.getObservation().setObservingStation(
					icaoToStationMap.get(context.getCurrentElement()));
		}
	}

	@Override
	public boolean canParseCurrentElement(EncodedWxStringParseContext context) {
		if(context.hasNextElement()){
			return isPotentialIcaoElement(context.getCurrentElement()) 
					&& isAfterCurrentElement(context.getCurrentPosition(), context.getElements(), 
							new Predicate(){
								@Override
								public boolean isMatch(String testElement) {
									return isDateTimeElement(testElement);
								}});
		}
		return false;
	}

	private static Map<String, Station> createStationMap() {
	
		Map<String, Station> stations = new HashMap<String, Station>();
		
		Station knfg = new Station();
		knfg.setIcaoStationIdentifier("KNFG");
		knfg.setStationName("MCAS Camp Pendleton, CA");
		knfg.setStationElevation(Height.valueOf(78d, NonSI.FOOT));
		knfg.setWmoStationIdentifier("66666");
		knfg.setStationCoordinates(LatLong.valueOf(33.3011139, -117.3551472, NonSI.DEGREE_ANGLE));
		
		stations.put(knfg.getIcaoStationIdentifier(), knfg);
		
		return stations;
	}
	
}
