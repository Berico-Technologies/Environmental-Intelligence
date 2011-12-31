package com.berico.ei;

import javax.measure.quantity.Pressure;
import javax.measure.quantity.Temperature;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.joda.time.DateTime;
import org.jscience.physics.amount.Amount;

public class Observation {

	public enum ObservationTypes {
		METAR ("hourly"),
		SPECI ("special conditions");
		
		private final String obtype;
		
		public String observationType(){
			return obtype;
		}
		
		ObservationTypes(String obtype){
			this.obtype = obtype;
		}
	}
	
	protected Station observingStation = null;
	
	protected ObservationTypes observationType = ObservationTypes.METAR;
	
	protected DateTime timeOfObservation = null;
	
	protected Winds winds = null;
	
	protected Visibility visibility = null;
	
	protected SkyCondition skyCondition = null;
	
	protected Amount<Temperature> airTemperature = null;
	
	protected Amount<Temperature> dewPoint = null;
	
	protected Amount<Pressure> altimeter = null;
	
	protected Amount<Pressure> seaLevelPressure = null;

	
	public Station getObservingStation() {
		return observingStation;
	}

	public void setObservingStation(Station observingStation) {
		this.observingStation = observingStation;
	}

	public ObservationTypes getObservationType() {
		return observationType;
	}

	public void setObservationType(ObservationTypes observationType) {
		this.observationType = observationType;
	}

	public DateTime getTimeOfObservation() {
		return timeOfObservation;
	}

	public void setTimeOfObservation(DateTime timeOfObservation) {
		this.timeOfObservation = timeOfObservation;
	}

	public Winds getWinds() {
		return winds;
	}

	public void setWinds(Winds winds) {
		this.winds = winds;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public SkyCondition getSkyCondition() {
		return skyCondition;
	}

	public void setSkyCondition(SkyCondition skyCondition) {
		this.skyCondition = skyCondition;
	}

	public Amount<Temperature> getAirTemperature() {
		return airTemperature;
	}

	public void setAirTemperature(Amount<Temperature> airTemperature) {
		this.airTemperature = airTemperature;
	}

	public Amount<Temperature> getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(Amount<Temperature> dewPoint) {
		this.dewPoint = dewPoint;
	}

	public Amount<Pressure> getAltimeter() {
		return altimeter;
	}

	public void setAltimeter(Amount<Pressure> altimeter) {
		this.altimeter = altimeter;
	}

	public Amount<Pressure> getSeaLevelPressure() {
		return seaLevelPressure;
	}

	public void setSeaLevelPressure(Amount<Pressure> seaLevelPressure) {
		this.seaLevelPressure = seaLevelPressure;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Weather Observation: ").append(this.getTimeOfObservation()).append("\n");
		sb.append(this.getObservingStation());
		sb.append("\tObservation Type: ").append(this.getObservationType()).append("\n");
		sb.append(this.getWinds());
		sb.append(this.getVisibility());
		sb.append(this.getSkyCondition());
		
		sb.append("Temperature: ").append(this.getAirTemperature().doubleValue(SI.CELSIUS)).append(" C\n");
		sb.append("Dew Point: ").append(this.getDewPoint().doubleValue(SI.CELSIUS)).append(" C\n");
		sb.append("Altimeter: ").append(this.getAltimeter().doubleValue(NonSI.INCH_OF_MERCURY)).append(" in\n");
		sb.append("Sea Level Pressure: ").append(this.getSeaLevelPressure().doubleValue(SI.MILLI(NonSI.BAR))).append(" mb\n");
		
		
		return sb.toString();
	}
	
}
