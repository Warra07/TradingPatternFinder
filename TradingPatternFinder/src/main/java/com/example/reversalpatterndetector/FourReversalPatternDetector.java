package com.example.reversalpatterndetector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.example.candlecore.CandleQueue;
import com.example.candlecore.Candlestick;
import com.example.candlecore.TYPE_CANDLESTICK;
import com.example.candlecore.TYPE_TREND;

public abstract class FourReversalPatternDetector {
	
	
	public static HashMap<String,Boolean> patternReversalCheking(HashMap<String, Boolean> patterns) {
		if(patterns == null) {
			patterns = new HashMap<String, Boolean>();
		}
		
		for( FOUR_REVERSAL_PATTERN p : FOUR_REVERSAL_PATTERN.values()) {
			Method ismethod = null;
			try {
				
				ismethod = FourReversalPatternDetector.class.getDeclaredMethod(p.getMethodName());
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
	
	public static Boolean concealingBabySwallow() {
		
		Candlestick one = CandleQueue.getCandle(3);

		Candlestick two = CandleQueue.getCandle(2);

		Candlestick three  = CandleQueue.getCandle(1);

		Candlestick four  = CandleQueue.getCandle(0);
		
	      return (one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && !two.isBull() && !three.isBull() && !four.isBull() &&
	    	         one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG) && two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG) && three.isType(TYPE_CANDLESTICK.CAND_SHORT) && 
	    	         three.getOpen()<two.getClose() && three.getHigh()>two.getClose() && 
	    	         four.getOpen()>three.getHigh() && four.getClose()<three.getLow());

	}
	
}
