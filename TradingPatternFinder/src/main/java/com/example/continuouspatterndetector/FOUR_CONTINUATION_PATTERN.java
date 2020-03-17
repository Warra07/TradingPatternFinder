package com.example.continuouspatterndetector;

public enum FOUR_CONTINUATION_PATTERN {
	
	THREE_LINE_STRIKE_BULLISH("threeLineStrikeBullish"),
	THREE_LINE_STIKE_BEARISH("threeLineStrikeBearish");
	
	
	private String methodName;

	FOUR_CONTINUATION_PATTERN(String method) {
		this.methodName = method;
	}
	
	public String getMethodName() {
		return this.methodName;
	}

}
