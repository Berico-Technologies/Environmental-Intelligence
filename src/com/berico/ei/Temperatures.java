package com.berico.ei;

import javax.measure.Measurable;
import javax.measure.quantity.Temperature;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static com.berico.ei.ConversionUtils.*;

public class Temperatures {

	protected Measurable<Temperature> ambientTemperature = null;
	
	protected Measurable<Temperature> threeOrSixHourMaximumTemperature = null;
	
	protected Measurable<Temperature> threeOrSixHourMinimumTemperature = null;
	
	protected Measurable<Temperature> twentyFourHourMaximumTemperature = null;
	
	protected Measurable<Temperature> twentyFourHourMinimumTemperature = null;
	
	protected Measurable<Temperature> dewpoint = null;
	
	protected double relativeHumidity = Double.MIN_VALUE;

	public Measurable<Temperature> getAmbientAirTemperature() {
		return ambientTemperature;
	}

	public void setAmbientTemperature(Measurable<Temperature> ambientTemperature) {
		this.ambientTemperature = ambientTemperature;
	}

	public Measurable<Temperature> getThreeOrSixHourMaximumTemperature() {
		return threeOrSixHourMaximumTemperature;
	}

	public void setThreeOrSixHourMaximumTemperature(Measurable<Temperature> maximumTemperature) {
		this.threeOrSixHourMaximumTemperature = maximumTemperature;
	}

	public Measurable<Temperature> getThreeOrSixHourMinimumTemperature() {
		return threeOrSixHourMinimumTemperature;
	}

	public void setThreeOrSixHourMinimumTemperature(Measurable<Temperature> minimumTemperature) {
		this.threeOrSixHourMinimumTemperature = minimumTemperature;
	}
	
	public Measurable<Temperature> getTwentyFourHourMaximumTemperature() {
		return twentyFourHourMaximumTemperature;
	}

	public void setTwentyFourHourMaximumTemperature(
			Measurable<Temperature> twentyFourHourMaximumTemperature) {
		this.twentyFourHourMaximumTemperature = twentyFourHourMaximumTemperature;
	}

	public Measurable<Temperature> getTwentyFourHourMinimumTemperature() {
		return twentyFourHourMinimumTemperature;
	}

	public void setTwentyFourHourMinimumTemperature(
			Measurable<Temperature> twentyFourHourMinimumTemperature) {
		this.twentyFourHourMinimumTemperature = twentyFourHourMinimumTemperature;
	}

	public Measurable<Temperature> getDewpoint() {
		return dewpoint;
	}

	public void setDewpoint(Measurable<Temperature> dewpoint) {
		this.dewpoint = dewpoint;
	}

	public double getRelativeHumidity() {
		return relativeHumidity;
	}

	public void setRelativeHumidity(double relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	
	public String toString(){
		
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Air Temperature", toC(getAmbientAirTemperature()))
			.append("Dewpoint", toC(getDewpoint()))
			.append("3/6 Hour Max Temp", toC(getThreeOrSixHourMaximumTemperature()))
			.append("3/6 Hour Min Temp", toC(getThreeOrSixHourMinimumTemperature()))
			.append("24 Hour Max Temp", toC(getTwentyFourHourMaximumTemperature()))
			.append("24 Hour Min Temp", toC(getTwentyFourHourMinimumTemperature()))
			.toString();
	}
	
}
