package com.example.reversalpatterndetector;

public enum MULTIPLE_REVERSAL_PATTERN {
	TEST_FUNCTION("testFunction");
	
	private String methodName;
	
	MULTIPLE_REVERSAL_PATTERN(String method) {
		this.methodName = method;
	}
	
	public String getMethodName() {
		return this.methodName;
	}

}
