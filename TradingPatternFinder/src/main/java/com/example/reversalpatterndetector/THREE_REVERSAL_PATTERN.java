package com.example.reversalpatterndetector;

public enum THREE_REVERSAL_PATTERN {
	
	ABANDONED_BABY_BULLISH("abandonedBabyBullish"),
	ABANDONED_BABY_BEARISH("abandonedBabyBearish"),
	MORNING_STAR("morningStar"),
	EVENING_STAR("eveningStar"),
	MORNING_DOJI_STAR("morningDojiStar"),
	EVENING_DOJI_STAR("eveningDojiStar"),
	BEARISH_UPSIDE_GAP_TWO_CROWS("bearishUpsideGapTwoCrows"),
	TWO_CROWS("twoCrows"),
	THREE_STAR_IN_THE_SOUTH("threeStarInTheSouth"),
	DELIBERATION("deliberation"),
	THREE_WHITE_SOLDIERS("threeWhiteSoldiers"),
	THREE_BLACK_CROWS("threeBlackCrows"),
	THREE_OUTSIDE_UP("threeOutsideUp"),
	THREE_OUTSIDE_DOWN("threeOutsideDown"),
	THREE_INSIDE_UP("threeInsideUp"),
	THREE_INSIDE_DOWN("threeInsideDown"),
	THREE_STARS_BULLISH("threeStarsBullish"),
	THREE_STARS_BEARISH("threeStarsBearish"),
	IDENTICAL_THREE_CROWS("identicalThreeCrows"),
	UNIQUE_THREE_RIVER_BOTTOM("uniqueThreeRiverBottom");
	
	private String methodName;
	
	THREE_REVERSAL_PATTERN(String method) {
		this.methodName = method;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
}
