package com.berico.ei;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jscience.geography.coordinates.Height;

import static com.berico.ei.ConversionUtils.*;

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
	
	@Override
	public String toString(){
		
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Coverage", getCoverage())
			.append("Height", toFt(getLayerBaseHeight()))
			.toString();
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
		return toFt(one) == toFt(two);
	}
	
	public static boolean isLower(CloudLayer layer1, CloudLayer layer2){
		
		double layer1Height = toFt(layer1.getLayerBaseHeight());
		double layer2Height = toFt(layer2.getLayerBaseHeight());
		
		return layer1Height < layer2Height;
	}
	
}
