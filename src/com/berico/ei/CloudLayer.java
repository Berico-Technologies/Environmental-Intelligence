package com.berico.ei;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.jscience.geography.coordinates.Height;


public class CloudLayer {
	
	private SkyCoverage layerCoverage = null;
	private Height layerBaseHeight = null;
	
	public CloudLayer(SkyCoverage layerCoverage, Height layerHeight){
		this.layerCoverage = layerCoverage;
		this.layerBaseHeight = layerHeight;
	}
	
	public SkyCoverage getCoverage(){
		return this.layerCoverage;
	}
	
	public Height getLayerBaseHeight(){
		return this.layerBaseHeight;
	}
	
	public double getLayerBaseHeightInFeet(){
		return this.layerBaseHeight.doubleValue(NonSI.FOOT);
	}
	
	public double getLayerBaseHeightInMeters(){
		return this.layerBaseHeight.doubleValue(SI.METER);
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Layer: ").append(this.layerCoverage).append(" ").append(this.layerBaseHeight.longValue(NonSI.FOOT)).append(" ft");
		
		return sb.toString();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((layerBaseHeight == null) ? 0 : layerBaseHeight.hashCode());
		result = prime * result
				+ ((layerCoverage == null) ? 0 : layerCoverage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CloudLayer other = (CloudLayer) obj;
		if (layerBaseHeight == null) {
			if (other.layerBaseHeight != null)
				return false;
		} else if (!sameHeight(layerBaseHeight, other.layerBaseHeight))
			return false;
		if (layerCoverage != other.layerCoverage)
			return false;
		return true;
	}


	public static boolean sameHeight(Height one, Height two){
		return one.longValue(NonSI.FOOT) == two.longValue(NonSI.FOOT);
	}
	
	public static boolean isLower(CloudLayer layer1, CloudLayer layer2){
		
		long layer1Height = layer1.getLayerBaseHeight().longValue(NonSI.FOOT);
		long layer2Height = layer2.getLayerBaseHeight().longValue(NonSI.FOOT);
		
		return layer1Height < layer2Height;
	}
}
