package com.berico.ei;

public class CloudEtages {

	protected Low low = Low.Unknown;
	
	protected Mid mid = Mid.Unknown;
	
	protected High high = High.Unknown;
	
	public Low getLow() {
		return low;
	}

	public void setLow(Low low) {
		this.low = low;
	}

	public Mid getMid() {
		return mid;
	}

	public void setMid(Mid mid) {
		this.mid = mid;
	}

	public High getHigh() {
		return high;
	}

	public void setHigh(High high) {
		this.high = high;
	}

	
	public enum Low {
		Unknown (-1, "Unknown"),
		None (0, "None"),
		FairWxCu (1, "Fair Weather Cumulus"),
		TCU (2, "Towering Cumulus"),
		CB (3, "Cumulonimbus"),
		ScFromCu (4, "Stratocumulus from Cumulus"),
		ScNotFromCu (5, "Stratocumulus not from Cumulus"),
		FairWxStratFrac (6, "Fair Weather Stratus Fractus"),
		BadWxStratFracCuFrac (7, "Bad Weather Stratus Fractus or Cumulus Fractus"),
		CuScMix (8, "Cumulus/Stratocumulus Mix"),
		CBwithAnvil (9, "Cumulonimbus with Anvil");
		
		private final int code;
		private final String description;
		
		Low(int code, String description){
			this.code = code;
			this.description = description;
		}
		
		public int getCode(){ return this.code; }
		public String getDescription(){ return this.description; }
		
		public static Low fromCode(int code){
			assert (code + 1) < Low.values().length - 1;
			return Low.values()[code + 1];
		}
	}
	
	public enum Mid {
		Unknown (-1, "Unknown"),
		None (0, "None"),
		ThinAs (1, "Thin Altostratus"),
		ThickAs (2, "Thick Altostratus"),
		ThinAc (3, "Thin Altocumulus"),
		PatchyAc (4, "Patchy Altocumulus"),
		ThickeningAc (5, "Thickening Altocumulus"),
		AcFromCu (6, "Altocumulus from Cumulus"),
		AcWithAsNs (7, "Altocumulus with Altostratus or Nimbostratus present"),
		AcCas (8, "Altocumulus Castellanus"),
		AcChoatic (9, "Altocumulus of Chaotic Skies");
		
		private final int code;
		private final String description;
		
		Mid(int code, String description){
			this.code = code;
			this.description = description;
		}
		
		public int getCode(){ return this.code; }
		public String getDescription(){ return this.description; }
		
		public static Mid fromCode(int code){
			assert (code + 1) < Mid.values().length - 1;
			return Mid.values()[code + 1];
		}
	}
	
	public enum High {
		Unknown (-1, "Unknown"), 
		None (0, "None"),
		CiFilaments (1, "Cirrus filaments (horsetails)"),
		CiDense (2, "Dense Cirrus"),
		CiFromCB (3, "Cirrus produced by Cumulunimbus anvil"),
		CiThickening (4, "Thickening Cirrus"),
		CsInvadingSkyLow (5, "Cirrostratus Invading the sky, less than 45% of horizon"),
		CsInvadingSkyHigh (6, "Cirrostratus Invading the sky, more than 45% of horizon"),
		CsEntireSky (7, "Cirrostratus covering entire sky"),
		CsPartialSky (8, "Cirrostratus partially covering sky"),
		Cc (9, "Cirrocumulus");
		
		private final int code;
		private final String description;
		
		High(int code, String description){
			this.code = code;
			this.description = description;
		}
		
		public int getCode(){ return this.code; }
		public String getDescription(){ return this.description; }
		
		public static High fromCode(int code){
			assert (code + 1) < High.values().length - 1;
			return High.values()[code + 1];
		}
	}
	
}
