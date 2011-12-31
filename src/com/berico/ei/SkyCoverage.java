package com.berico.ei;

public enum SkyCoverage {
	Clear ("SKC"),
	Few ("FEW"),
	Scattered ("SCT"),
	Broken ("BKN"),
	Overcast ("OVC"),
	VerticalVisibility ("VV");
	
	private final String encodedValue;
	
	public String encodedValue(){
		return this.encodedValue;
	}
	
	public static SkyCoverage fromEncodedString(String encodedString) {
	    if (encodedString != null) {
	     if(encodedString.equalsIgnoreCase("CLR")){
	    	 return SkyCoverage.Clear;
	     }
	      for (SkyCoverage coverage : SkyCoverage.values()) {
	        if (encodedString.equalsIgnoreCase(coverage.encodedValue())) {
	          return coverage;
	        }
	      }
	    }
	    return null;
	  }
	
	SkyCoverage(String encodedValue){
		String normalizedValue = encodedValue;
		if(encodedValue.equals("CLR")){
			normalizedValue = "SKC";
		}
		this.encodedValue = normalizedValue;
	}
	
	public static boolean isCeilingCoverage(SkyCoverage coverage){
		switch(coverage){
			case Broken:
			case Overcast:
			case VerticalVisibility: return true;
			default: return false;
		}
	}
}