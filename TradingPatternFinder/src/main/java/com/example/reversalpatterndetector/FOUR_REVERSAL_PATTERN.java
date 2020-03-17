package com.example.reversalpatterndetector;

public enum FOUR_REVERSAL_PATTERN {
	
	CONCEALING_BABY_SWALLOW("concealingBabySwallow");
	
	private String methodName;
	
	FOUR_REVERSAL_PATTERN(String method) {
		this.methodName = method;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
	

}
