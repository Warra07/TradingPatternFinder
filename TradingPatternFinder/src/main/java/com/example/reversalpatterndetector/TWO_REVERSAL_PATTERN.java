package com.example.reversalpatterndetector;

public enum TWO_REVERSAL_PATTERN {

	ENGULFING_BULLISH("engulfingBullish"),
	ENGULFING_BEARISH("engulfingBearish"),
	HARAMI_CROSS_BULLISH("haramiCrossBullish"),
	HARAMI_CROSS_BEARISH("haramiCrossBearish"),
	HARAMI_BULLISH("haramiBullish"),
	HARAMI_BEARISH("haramiBearish"),
	DOJI_STAR_BULLISH("dojiStarBullish"),
	DOJI_STAR_BEARISH("dojiStarBearish"),
	PEARCING_PATTERN("pearcingPattern"),
	DARK_CLOUD_COVER("darkCloudCover"),
	MEETING_LINES_BULLISH("meetingLinesBullish"),
	MEETING_LINES_BEARISH("meetingLinesBearish"),
	MATCHING_LOW("matchingLow"),
	HOMING_PIGEON("homingPigeon");
		
		private String methodName;
		
		TWO_REVERSAL_PATTERN(String method) {
			this.methodName = method;
		}
		
		public String getMethodName() {
			return this.methodName;
		}
}
