package com.example.continuouspatterndetector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.example.candlecore.CandleQueue;
import com.example.candlecore.Candlestick;
import com.example.candlecore.TYPE_CANDLESTICK;
import com.example.candlecore.TYPE_TREND;
import com.example.reversalpatterndetector.OneReversalPatternDetector;

public abstract class FourContinuationPatternDetector {

	
	public static HashMap<String,Boolean> patternReversalCheking(HashMap<String, Boolean> patterns) {
		if(patterns == null) {
			patterns = new HashMap<String, Boolean>();
		}
		
		for( FOUR_CONTINUATION_PATTERN p : FOUR_CONTINUATION_PATTERN.values()) {
			Method ismethod = null;
			try {
				
				ismethod = FourContinuationPatternDetector.class.getDeclaredMethod(p.getMethodName());
				patterns.put(p.getMethodName(), (Boolean) ismethod.invoke(null));
				
			} catch (NoSuchMethodException | SecurityException |
					IllegalAccessException | IllegalArgumentException |
					InvocationTargetException  e) {
				e.printStackTrace();
			}

		}
		
		return patterns;
	}
	
	public static Boolean threeLineStrikeBullish() {
		
		Candlestick one = CandleQueue.getCandle(3);

		Candlestick two = CandleQueue.getCandle(2);

		Candlestick three  = CandleQueue.getCandle(1);

		Candlestick four  = CandleQueue.getCandle(0);
		
		
		if(one.isTrend(TYPE_TREND.UPPER) && one.isBull() && two.isBull() && three.isBull() && !four.isBull() && 
				   (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
				   && (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
				   (three.isType(TYPE_CANDLESTICK.CAND_LONG) || three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
				   two.getClose()>one.getClose() && three.getClose()>two.getClose() && four.getClose()<one.getOpen()) 
				  {
				      if(four.getOpen()>three.getClose()) 
				        {
				        return true; 
				        }
				     
				  }
		return false;
	}
	
	
	public static Boolean threeLineStrikeBearish() {
		
		Candlestick one = CandleQueue.getCandle(3);

		Candlestick two = CandleQueue.getCandle(2);

		Candlestick three  = CandleQueue.getCandle(1);

		Candlestick four  = CandleQueue.getCandle(0);
		
		if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && !two.isBull() && !three.isBull() && four.isBull() && 
				   (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG))
				   && (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
				   (three.isType(TYPE_CANDLESTICK.CAND_LONG) || three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
				   two.getClose()<one.getClose() && three.getClose()<two.getClose() && four.getClose()>one.getOpen()) 
				  {

				      if(four.getOpen()<three.getClose()) 
				        {
				    	  return true;
				        }
				     
				  }
		return false;
		
		
	}
}
