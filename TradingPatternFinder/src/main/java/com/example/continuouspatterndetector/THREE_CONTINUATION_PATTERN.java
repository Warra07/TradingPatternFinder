package com.example.continuouspatterndetector;

public enum THREE_CONTINUATION_PATTERN {
	
	UPSIDE_GAP_THREE_BULLISH("upsideGapThreeBullish"),
	UPSIDE_GAP_THREE_BEARISH("upsideGapThreeBearish"),
	UPSIDE_TATSUKI_GAP_BULLISH("upsideTatsukiGapBullish"),
	UPSIDE_TATSUKI_GAP_BEARISH("upsideTatsukiGapBearish");
	
	private String methodName;

	
	THREE_CONTINUATION_PATTERN(String method) {
		this.methodName = method;
	}
	
	public String getMethodName() {
		return this.methodName;
	}

}
