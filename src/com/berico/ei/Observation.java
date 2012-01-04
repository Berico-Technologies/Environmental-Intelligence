package com.berico.ei;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.DateTime;

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
	
	protected Pressures pressures = new Pressures();

	
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

	public Pressures getPressures() {
		return pressures;
	}

	public void setPressures(Pressures pressures) {
		this.pressures = pressures;
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
			.append("Pressures", this.getPressures())
			.toString();
	}
	
}
