package com.example.continuouspatterndetector;

public enum TWO_CONTINUATION_PATTERN {
	
	KICKING_BULLISH("kickingBullish"),
	KICKING_BEARISH("kickingBearish"),
	ON_NECK_LINE("onNeckLine"),
	IN_NECK_LINE("inNeckLine"),
	THRUSTING_LINE("thrustingLine");
	
	private String methodName;
	
	TWO_CONTINUATION_PATTERN(String method) {
		this.methodName = method;
	}
	
	public String getMethodName() {
		return this.methodName;
	}

}
