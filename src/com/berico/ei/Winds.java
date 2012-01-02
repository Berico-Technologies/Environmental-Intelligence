package com.berico.ei;

import javax.measure.Measurable;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static com.berico.ei.ConversionUtils.*;

public class Winds {

	protected boolean isVariableDirection = false;
	
	protected Measurable<Angle> windDirection = null;
	
	protected Measurable<Velocity> windSpeed = null;
	
	protected Measurable<Velocity> windGust = null;
	
	protected Measurable<Angle> variationStart = null;
	
	protected Measurable<Angle> variationEnd = null;

	public boolean isVariable(){
		
		if(windSpeed.longValue(NonSI.KNOT) <= 6){
			return this.isVariableDirection;
		}
		return (this.variationStart != null && this.variationEnd != null);
	}
	
	public boolean hasGusts(){
		return this.windGust != null;
	}
	
	public void setIsVariable(boolean trueIfVRB){
		this.isVariableDirection = trueIfVRB;
	}
	
	public Measurable<Angle> getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(Measurable<Angle> windDirection) {
		this.windDirection = windDirection;
	}

	public Measurable<Velocity> getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Measurable<Velocity> windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Measurable<Velocity> getWindGust() {
		return windGust;
	}

	public void setWindGust(Measurable<Velocity> windGust) {
		this.windGust = windGust;
	}

	public Measurable<Angle> getVariationStart() {
		return variationStart;
	}

	public void setVariationStart(Measurable<Angle> variationStart) {
		this.variationStart = variationStart;
	}

	public Measurable<Angle> getVariationEnd() {
		return variationEnd;
	}

	public void setVariationEnd(Measurable<Angle> variationEnd) {
		this.variationEnd = variationEnd;
	}

	@Override
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Direction", toDeg(getWindDirection()))
			.append("Is Variable", isVariable())
			.append("Speed", toKnots(getWindSpeed()))
			.append("Has Gusts", hasGusts())
			.append("Gust", toKnots(getWindGust()))
			.toString();
	}
	
	
	
}
