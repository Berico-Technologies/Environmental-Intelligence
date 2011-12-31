package com.berico.ei;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Measurable;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.unit.NonSI;

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
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Visibility: ").append(this.prevailingVisibility.doubleValue(NonSI.MILE)).append(" miles\n");
		
		return sb.toString();
	}
}
