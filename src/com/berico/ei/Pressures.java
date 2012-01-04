package com.berico.ei;

import javax.measure.Measurable;
import javax.measure.quantity.Pressure;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static com.berico.ei.ConversionUtils.*;

public class Pressures {

	protected Measurable<Pressure> seaLevelPressure = null;
	
	protected Measurable<Pressure> altimeter = null;

	public Measurable<Pressure> getSeaLevelPressure() {
		return seaLevelPressure;
	}

	public void setSeaLevelPressure(Measurable<Pressure> seaLevelPressure) {
		this.seaLevelPressure = seaLevelPressure;
	}

	public Measurable<Pressure> getAltimeter() {
		return altimeter;
	}

	public void setAltimeter(Measurable<Pressure> altimeter) {
		this.altimeter = altimeter;
	}
	
	public String toString(){
		
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Altimeter Setting", toInHg(getAltimeter()))
			.append("Sea Level Pressure", toMb(getSeaLevelPressure()))
			.toString();
	}
	
}
