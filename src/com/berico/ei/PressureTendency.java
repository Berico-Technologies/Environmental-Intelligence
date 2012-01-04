package com.berico.ei;

import javax.measure.Measurable;
import javax.measure.quantity.Pressure;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PressureTendency {
	
	protected Measurable<Pressure> magnitudeOfChange = null;
	
	protected ChangeCategory changeCategory = ChangeCategory.Steady;

	public Measurable<Pressure> getMagnitudeOfChange() {
		return magnitudeOfChange;
	}

	public void setMagnitudeOfChange(Measurable<Pressure> magnitudeOfChange) {
		this.magnitudeOfChange = magnitudeOfChange;
	}

	public ChangeCategory getChangeCategory() {
		return changeCategory;
	}

	public void setChangeCategory(ChangeCategory changeCategory) {
		this.changeCategory = changeCategory;
	}
	
	public String toString(){
		
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Change Category", getChangeCategory())
			.append("Magnitude of Change", getMagnitudeOfChange())
			.toString();
	}

	public enum ChangeCategory {
		
		IncrThenDecr (0, "Increasing then decreasing"),
		IncrThenSteadyOrIncrMoreSlowly (1, "Increasing then steady or increasing, then increasing more slowly"),
		Increasing (3, "Increasing steadily or unsteadily"),
		DecrOrSteadyThenIncrRapidly (4, "Decreasing or steady then increasing; Increasing then increasing more rapidly"),
		Steady (4, "Steady"),
		DecrThenIncr (5, "Decreasing then increasing"),
		DecrThenSteadyOrDecrMoreSlowly (6, "Decreasing then steady or decreasing, then decreasing more slowly"),
		Decreasing (7, "Decreasing steadily or unsteadily"),
		IncrOrSteadyThenDecrRapidly (8, "Increasing or steady then decreasing; Decreasing then decreasing more rapidly");
		
		private final int code;
		private final String description;
		
		ChangeCategory(int code, String description){
			this.code = code;
			this.description = description;
		}
		
		public int getCode(){
			return this.code;
		}
		
		public String getDescription(){
			return this.description;
		}
		
		public String toString(){
			return String.format("[%s] %s", this.code, this.description);
		}
		
		public static ChangeCategory fromCode(int code){
			assert code >= 0 && code < ChangeCategory.values().length;
			return ChangeCategory.values()[code];
		}
	}
	
}
