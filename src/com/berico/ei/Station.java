package com.berico.ei;

import javax.measure.unit.NonSI;

import org.jscience.geography.coordinates.Height;
import org.jscience.geography.coordinates.LatLong;

public class Station {

	private String stationName = null;
	private String icaoStationIdentifier = null;
	private String wmoStationIdentifier = null;
	private LatLong stationCoordinates = null;
	private Height stationElevation = null;
	
	public String getStationName() {
		return stationName;
	}
	
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	public String getIcaoStationIdentifier() {
		return icaoStationIdentifier;
	}
	
	public void setIcaoStationIdentifier(String icaoStationIdentifier) {
		this.icaoStationIdentifier = icaoStationIdentifier;
	}
	
	public String getWmoStationIdentifier() {
		return wmoStationIdentifier;
	}
	
	public void setWmoStationIdentifier(String wmoStationIdentifier) {
		this.wmoStationIdentifier = wmoStationIdentifier;
	}
	
	public LatLong getStationCoordinates() {
		return stationCoordinates;
	}
	
	public void setStationCoordinates(LatLong stationCoordinates) {
		this.stationCoordinates = stationCoordinates;
	}
	
	public Height getStationElevation() {
		return stationElevation;
	}
	
	public void setStationElevation(Height stationElevation) {
		this.stationElevation = stationElevation;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();

		sb.append("Station: ").append(this.getStationName()).append("\n");
		sb.append("\tICAO: ").append(this.getIcaoStationIdentifier()).append("\n");
		sb.append("\tWMO: ").append(this.getWmoStationIdentifier()).append("\n");
		sb.append("\tElevation: ").append(this.getStationElevation().doubleValue(NonSI.FOOT)).append("\n");
		sb.append("\tLat: ").append(this.getStationCoordinates().getCoordinates()[0]).append("\n");
		sb.append("\tLon: ").append(this.getStationCoordinates().getCoordinates()[1]).append("\n");
		
		return sb.toString();
	}
}
