package com.berico.ei;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.measure.unit.NonSI;

public class SkyCondition {

	protected CloudLayer ceiling = null;
	
	protected boolean isClear = false;
	
	protected Map<Long, CloudLayer> skyCondition = new TreeMap<Long, CloudLayer>();
	
	public void addLayer(CloudLayer layer){
		
		if(SkyCoverage.isCeilingCoverage(layer.getCoverage())){
			if(ceiling == null){
				
				this.setCeiling(layer);
			}
			else if(CloudLayer.isLower(layer, this.ceiling)) {
				
				this.setCeiling(layer);
			}
		}
		
		if(layer.getCoverage().equals(SkyCoverage.Clear)){
			this.isClear = true;
			return;
		}
		
		skyCondition.put(layer.getLayerBaseHeight().longValue(NonSI.FOOT), layer);
	}
	
	public Collection<CloudLayer> getLayers(){
		return skyCondition.values();
	}
	
	public boolean isClear(){
		return this.isClear;
	}
	
	public void setSkyClear(){
		this.isClear = true;
	}
	
	public boolean hasCeiling(){
		return ceiling != null;
	}
	
	public CloudLayer getCeiling(){
		return this.ceiling;
	}
	
	protected void setCeiling(CloudLayer layer){
		this.ceiling = layer;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Sky Condition: \n");
		sb.append("\tIs Clear?: ").append(isClear()).append("\n");
		sb.append("\tHas Ceiling?: ").append(hasCeiling()).append("\n");
		
		if(!isClear()){
			
			for(CloudLayer layer : getLayers()){
				sb.append("\t").append(layer).append("\n");
			}
			
		}
		
		return sb.toString();
	}
	
}
