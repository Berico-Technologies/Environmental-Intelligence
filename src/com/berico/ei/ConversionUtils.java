package com.berico.ei;

import javax.measure.Measurable;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Pressure;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.jscience.geography.coordinates.Height;
import org.jscience.physics.amount.Amount;

public class ConversionUtils {

	public static double toC(Measurable<Temperature> temp){
		if(temp == null){ return Double.MIN_VALUE; }
		return temp.doubleValue(SI.CELSIUS);
	}
	
	public static double toF(Measurable<Temperature> temp){
		if(temp == null){ return Double.MIN_VALUE; }
		return temp.doubleValue(NonSI.FAHRENHEIT);
	}
	
	public static double toFt(Height height){
		if(height == null){ return Double.MIN_VALUE; }
		return height.doubleValue(NonSI.FOOT);
	}
	
	public static double toFt(Measurable<Length> length){
		if(length == null){ return Double.MIN_VALUE; }
		return length.doubleValue(NonSI.FOOT);
	}
	
	public static double toM(Height height){
		if(height == null){ return Double.MIN_VALUE; }
		return height.doubleValue(SI.METER);
	}
	
	public static double toM(Measurable<Length> length){
		if(length == null){ return Double.MIN_VALUE; }
		return length.doubleValue(SI.METER);
	}
	
	public static double toKm(Height height){
		if(height == null){ return Double.MIN_VALUE; }
		return height.doubleValue(SI.KILOMETER);
	}
	
	public static double toKm(Measurable<Length> length){
		if(length == null){ return Double.MIN_VALUE; }
		return length.doubleValue(SI.KILOMETER);
	}
	
	public static double toMi(Height height){
		if(height == null){ return Double.MIN_VALUE; }
		return height.doubleValue(NonSI.MILE);
	}
	
	public static double toMi(Measurable<Length> length){
		if(length == null){ return Double.MIN_VALUE; }
		return length.doubleValue(NonSI.MILE);
	}
	
	public static double toMph(Measurable<Velocity> velocity){
		if(velocity == null){ return Double.MIN_VALUE; }
		return velocity.doubleValue(NonSI.MILES_PER_HOUR);
	}
	
	public static double toKnots(Measurable<Velocity> velocity){
		if(velocity == null){ return Double.MIN_VALUE; }
		return velocity.doubleValue(NonSI.KNOT);
	}
	
	public static long toDeg(Measurable<Angle> angle){
		if(angle == null){ return Integer.MIN_VALUE; }
		return angle.longValue(NonSI.DEGREE_ANGLE);
	}
	
	public static long toRad(Measurable<Angle> angle){
		if(angle == null){ return Integer.MIN_VALUE; }
		return angle.longValue(SI.RADIAN);
	}
	
	public static double toInHg(Measurable<Pressure> pressure){
		if(pressure == null) { return Double.MIN_VALUE; }
		return pressure.doubleValue(NonSI.INCH_OF_MERCURY);
	}
	
	public static double toMb(Measurable<Pressure> pressure){
		if(pressure == null) { return Double.MIN_VALUE; }
		return pressure.doubleValue(SI.MILLI(NonSI.BAR));
	}
	
	public static Measurable<Temperature> fromC(double temp){
		return Amount.valueOf(temp, SI.CELSIUS);
	}
}
