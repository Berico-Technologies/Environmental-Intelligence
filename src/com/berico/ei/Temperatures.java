package com.berico.ei;

import javax.measure.Measurable;
import javax.measure.quantity.Temperature;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static com.berico.ei.ConversionUtils.*;

public class Temperatures {

	protected Measurable<Temperature> ambientTemperature = null;
	
	protected Measurable<Temperature> maximumTemperature = null;
	
	protected Measurable<Temperature> minimumTemperature = null;
	
	protected Measurable<Temperature> dewpoint = null;
	
	protected double relativeHumidity = Double.MIN_VALUE;

	public Measurable<Temperature> getAmbientAirTemperature() {
		return ambientTemperature;
	}

	public void setAmbientTemperature(Measurable<Temperature> ambientTemperature) {
		this.ambientTemperature = ambientTemperature;
	}

	public Measurable<Temperature> getMaximumTemperature() {
		return maximumTemperature;
	}

	public void setMaximumTemperature(Measurable<Temperature> maximumTemperature) {
		this.maximumTemperature = maximumTemperature;
	}

	public Measurable<Temperature> getMinimumTemperature() {
		return minimumTemperature;
	}

	public void setMinimumTemperature(Measurable<Temperature> minimumTemperature) {
		this.minimumTemperature = minimumTemperature;
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
			.append("Air Temperature", toC(this.ambientTemperature))
			.append("Dewpoint", toC(this.dewpoint))
			.append("Max Temp", toC(this.maximumTemperature))
			.append("Min Temp", toC(this.minimumTemperature))
			.toString();
	}
	
}
