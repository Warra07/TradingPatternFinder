package com.example.continuouspatterndetector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.example.candlecore.CandleQueue;
import com.example.candlecore.Candlestick;
import com.example.candlecore.TYPE_CANDLESTICK;
import com.example.candlecore.TYPE_TREND;
import com.example.reversalpatterndetector.OneReversalPatternDetector;

public abstract class TwoContinuationPatternDetector {
	
	public static HashMap<String,Boolean> patternReversalCheking(HashMap<String, Boolean> patterns) {
		if(patterns == null) {
			patterns = new HashMap<String, Boolean>();
		}
		
		for( TWO_CONTINUATION_PATTERN p : TWO_CONTINUATION_PATTERN.values()) {
			Method ismethod = null;
			try {
				
				ismethod = TwoContinuationPatternDetector.class.getDeclaredMethod(p.getMethodName());
				patterns.put(p.getMethodName(), (Boolean) ismethod.invoke(null));
				
			} catch (NoSuchMethodException | SecurityException |
					IllegalAccessException | IllegalArgumentException |
					InvocationTargetException  e) {
				e.printStackTrace();
			}

		}
		
		return patterns;
	}
	
	public static Boolean kickingBullish() {
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
		return (!one.isBull() && two.isBull() && 
				   one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)
				   && two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG) && 
				   one.getOpen()<two.getOpen());
		
	}
	
	public static Boolean kickingBearish() {
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
		return (one.isBull() && !two.isBull() && 
				   one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG) 
				   && two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG) && 
				   one.getOpen()>two.getOpen());
		
	}
	
	public static Boolean onNeckLine() {
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
		if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isBull() && 
				   (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)))
				  {
				   
				   if(two.getOpen()<one.getLow() && two.getClose()==one.getLow()) 
				     {
					   return true;
				     }
				  }
		return false;
	}
	
	public static Boolean inNeckLine() {
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
		if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isBull() && 
				   (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG))) 
				  {
				   if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isBull() &&
					         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
					         one.getBodysize()>two.getBodysize() && 
					         two.getOpen()<one.getLow() && two.getClose()>=one.getClose() && two.getClose()<(one.getClose()+one.getBodysize()*0.01)) 
				   		{
					   return true;
				     }
				  }
		return false;
		
	}
	
	public static Boolean thrustingLine() {
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
		if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isBull() && 
				   (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG))) 
				  {
				   
				   if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isBull() && 
				            (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
				            two.getOpen()<one.getLow() && two.getClose()>one.getClose() && two.getClose()<(one.getOpen()+one.getClose())/2)
				     {
					   return true;
				     }
				  }
		return false;
		
	}

}
