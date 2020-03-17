package com.example.reversalpatterndetector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.example.candlecore.CandleQueue;
import com.example.candlecore.TYPE_CANDLESTICK;
import com.example.candlecore.TYPE_TREND;

public abstract class OneReversalPatternDetector {
	
	public static HashMap<String,Boolean> patternReversalCheking(HashMap<String, Boolean> patterns) {
		if(patterns == null) {
			patterns = new HashMap<String, Boolean>();
		}
		
		for( ONE_REVERSAL_PATTERN p : ONE_REVERSAL_PATTERN.	values()) {
			Method ismethod = null;
			try {
				
				ismethod = OneReversalPatternDetector.class.getDeclaredMethod(p.getMethodName());
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
	
	
	public static Boolean invertedHammer() {
		return CandleQueue.getCandle(0).isTrend(TYPE_TREND.DOWN) && CandleQueue.getCandle(0).isType(TYPE_CANDLESTICK.CAND_INVERT_HAMMER);
	}
	
	public static Boolean hammer() {
		return CandleQueue.getCandle(0).isTrend(TYPE_TREND.DOWN) && CandleQueue.getCandle(0).isType(TYPE_CANDLESTICK.CAND_HAMMER);
	}
	
	public static Boolean hangingMan() {
		return CandleQueue.getCandle(0).isTrend(TYPE_TREND.UPPER) && CandleQueue.getCandle(0).isType(TYPE_CANDLESTICK.CAND_HAMMER);
	}
	
	public static Boolean shootingStar() {
		return CandleQueue.getCandle(0).isTrend(TYPE_TREND.UPPER) && CandleQueue.getCandle(0).isType(TYPE_CANDLESTICK.CAND_INVERT_HAMMER);
	}
	
	public static Boolean beltHoldBullish() {
		return CandleQueue.getCandle(0).isTrend(TYPE_TREND.DOWN) && CandleQueue.getCandle(0).isType(TYPE_CANDLESTICK.CAND_HAMMER) 
				&& CandleQueue.getCandle(0).isBull();
	}
	
	public static Boolean beltHoldBearish() {
		return CandleQueue.getCandle(0).isTrend(TYPE_TREND.UPPER) && CandleQueue.getCandle(0).isType(TYPE_CANDLESTICK.CAND_HAMMER)
					&& !CandleQueue.getCandle(0).isBull();
				}


}
