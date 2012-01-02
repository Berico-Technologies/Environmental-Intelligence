package com.berico.ei;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jscience.geography.coordinates.Height;
import org.jscience.geography.coordinates.LatLong;

import static com.berico.ei.ConversionUtils.*;

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
	
	public double getLatitude(){
		return this.getStationCoordinates().getCoordinates()[0];
	}
	
	public double getLongitude(){
		return this.getStationCoordinates().getCoordinates()[1];
	}
	
	public Height getStationElevation() {
		return stationElevation;
	}
	
	public void setStationElevation(Height stationElevation) {
		this.stationElevation = stationElevation;
	}
	
	@Override
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Name", this.getStationName())
			.append("ICAO", this.getIcaoStationIdentifier())
			.append("WMO", this.getWmoStationIdentifier())
			.append("Elevation", toFt(this.getStationElevation()))
			.append("Latitude", this.getLatitude())
			.append("Longitude", this.getLongitude())
			.toString();
	}
	

}
