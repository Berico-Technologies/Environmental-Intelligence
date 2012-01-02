package com.berico.ei;

import javax.measure.quantity.Pressure;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
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
	
	protected Winds winds = new Winds();
	
	protected Visibility visibility = new Visibility();
	
	protected SkyCondition skyCondition = new SkyCondition();
	
	protected Temperatures temperatures = new Temperatures();
	
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

	public Temperatures getTemperatures(){
		return this.temperatures;
	}
	
	public void setTemperatures(Temperatures temperatures){
		this.temperatures = temperatures;
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
		
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Time of Observation", this.getTimeOfObservation())
			.append("Observing Station", this.getObservingStation())
			.append("Winds", this.getWinds())
			.append("Visibility", this.getVisibility())
			.append("Sky Condition", this.getSkyCondition())
			.append("Temperatures", this.getTemperatures())
			.append("Altimeter", this.getAltimeter().doubleValue(NonSI.INCH_OF_MERCURY))
			.append("Sea Level Pressure", this.getSeaLevelPressure().doubleValue(SI.MILLI(NonSI.BAR)))
			.toString();
	}
	
}
