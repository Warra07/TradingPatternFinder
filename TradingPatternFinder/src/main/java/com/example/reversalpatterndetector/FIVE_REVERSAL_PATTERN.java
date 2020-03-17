package com.example.reversalpatterndetector;

public enum FIVE_REVERSAL_PATTERN {

	BREAKAWAY_BULLISH("breakawayBullish"),
	BREAKAWAY_BEARISH("breakawayBearish");
	
	private String methodName;
	
	FIVE_REVERSAL_PATTERN(String method) {
		this.methodName = method;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
}
