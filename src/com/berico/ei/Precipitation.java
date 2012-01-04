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

	public Measurable<Length> getHourlyPrecipitation() {
		return hourlyPrecipitation;
	}

	public void setHourlyPrecipitation(Measurable<Length> hourlyPrecipitation) {
		this.hourlyPrecipitation = hourlyPrecipitation;
	}

	public Measurable<Length> getThreeOrSixHourPrecipitation() {
		return threeOrSixHourPrecipitation;
	}

	public void setThreeHourPrecipitation(Measurable<Length> threeOrSixHourPrecipitation) {
		this.threeOrSixHourPrecipitation = threeOrSixHourPrecipitation;
	}

	public Measurable<Length> getTwentyFourHourPrecipitation() {
		return twentyFourHourPrecipitation;
	}

	public void setTwentyFourHourPrecipitation(
			Measurable<Length> twentyFourHourPrecipitation) {
		this.twentyFourHourPrecipitation = twentyFourHourPrecipitation;
	}
	
	public String toString(){
		
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Hourly", toIn(getHourlyPrecipitation()))
			.append("3/6 Hour", toIn(getThreeOrSixHourPrecipitation()))
			.append("24 Hour", toIn(getTwentyFourHourPrecipitation()))
			.toString();
	}
	
}
