package com.berico.ei;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Measurable;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static com.berico.ei.ConversionUtils.*;

public class Visibility {

	protected Measurable<Length> prevailingVisibility = null;
	
	protected List<SectorVisibility> sectorVisibilities = new ArrayList<SectorVisibility>();
	
	public Measurable<Length> getPrevailingVisibility() {
		return prevailingVisibility;
	}

	public void setPrevailingVisibility(Measurable<Length> prevailingVisibility) {
		this.prevailingVisibility = prevailingVisibility;
	}

	public List<SectorVisibility> getSectorVisibilities() {
		return sectorVisibilities;
	}

	public void setSectorVisibilities(List<SectorVisibility> sectorVisibilities) {
		this.sectorVisibilities = sectorVisibilities;
	}

	public class SectorVisibility {
		
		protected Measurable<Length> visibility = null;
		protected Measurable<Angle> sector = null;
		
	}
	
	@Override
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Prevailing", toMi(getPrevailingVisibility()))
			.toString();
	}
}
