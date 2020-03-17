package com.example.reversalpatterndetector;

public enum ONE_REVERSAL_PATTERN {
	INVERTED_HAMMER("invertedHammer"),
	HAMMER("hammer"),
	HANGING_MAN("hangingMan"),
	SHOOTING_STAR("shootingStar"),
	BELT_HOLD_BULLISH("beltHoldBullish"),
	BELT_HOLD_BEARISH("beltHoldBearish");
	
	private String methodName;
	
	ONE_REVERSAL_PATTERN(String method) {
		this.methodName = method;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
	

}
