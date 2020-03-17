package com.example.reversalpatterndetector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class MultipleReversalPatternDetector {
	public static HashMap<String,Boolean> patternReversalCheking(HashMap<String, Boolean> patterns) {
		if(patterns == null) {
			patterns = new HashMap<String, Boolean>();
		}
		
		for( MULTIPLE_REVERSAL_PATTERN p : MULTIPLE_REVERSAL_PATTERN.values()) {
			Method ismethod = null;
			try {
				
				ismethod = MultipleReversalPatternDetector.class.getDeclaredMethod(p.getMethodName());
				patterns.put(p.getMethodName(), (Boolean) ismethod.invoke(null));
				
			} catch (NoSuchMethodException | SecurityException |
					IllegalAccessException | IllegalArgumentException |
					InvocationTargetException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return patterns;
	}
	
	public static Boolean testFunction() {
		return false;
	}

}
