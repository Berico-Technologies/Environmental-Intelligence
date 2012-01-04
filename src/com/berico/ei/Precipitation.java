package com.berico.ei;

import javax.measure.Measurable;
import javax.measure.quantity.Length;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static com.berico.ei.ConversionUtils.*;

public class Precipitation {

	protected Measurable<Length> hourlyPrecipitation = null;
	
	protected Measurable<Length> threeOrSixHourPrecipitation = null;
	
	protected Measurable<Length> twentyFourHourPrecipitation = null;
	
	protected Measurable<Length> snowDepthOnGround = null;
	
	protected Measurable<Length> liquidEquivalentOfSnowOnGround = null;

	public Measurable<Length> getHourlyPrecipitation() {
		return hourlyPrecipitation;
	}

	public void setHourlyPrecipitation(Measurable<Length> hourlyPrecipitation) {
		this.hourlyPrecipitation = hourlyPrecipitation;
	}

	public Measurable<Length> getThreeOrSixHourPrecipitation() {
		return threeOrSixHourPrecipitation;
	}

	public void setThreeOrSixHourPrecipitation(Measurable<Length> threeOrSixHourPrecipitation) {
		this.threeOrSixHourPrecipitation = threeOrSixHourPrecipitation;
	}

	public Measurable<Length> getTwentyFourHourPrecipitation() {
		return twentyFourHourPrecipitation;
	}

	public void setTwentyFourHourPrecipitation(
			Measurable<Length> twentyFourHourPrecipitation) {
		this.twentyFourHourPrecipitation = twentyFourHourPrecipitation;
	}
	
	public Measurable<Length> getSnowDepthOnGround() {
		return snowDepthOnGround;
	}

	public void setSnowDepthOnGround(Measurable<Length> snowDepthOnGround) {
		this.snowDepthOnGround = snowDepthOnGround;
	}

	public Measurable<Length> getLiquidEquivalentOfSnowOnGround() {
		return liquidEquivalentOfSnowOnGround;
	}

	public void setLiquidEquivalentOfSnowOnGround(
			Measurable<Length> liquidEquivalentOfSnowOnGround) {
		this.liquidEquivalentOfSnowOnGround = liquidEquivalentOfSnowOnGround;
	}

	public String toString(){
		
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Hourly", toIn(getHourlyPrecipitation()))
			.append("3/6 Hour", toIn(getThreeOrSixHourPrecipitation()))
			.append("24 Hour", toIn(getTwentyFourHourPrecipitation()))
			.append("Snow Depth", toIn(getSnowDepthOnGround()))
			.append("Liquid Equivalent of Snow on Ground", toIn(getLiquidEquivalentOfSnowOnGround()))
			.toString();
	}
	
}
