package com.example.continuouspatterndetector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.example.candlecore.CandleQueue;
import com.example.candlecore.Candlestick;
import com.example.candlecore.TYPE_CANDLESTICK;
import com.example.candlecore.TYPE_TREND;
import com.example.reversalpatterndetector.OneReversalPatternDetector;

public abstract class ThreeContinuationPatternDetector {
	
	public static HashMap<String,Boolean> patternReversalCheking(HashMap<String, Boolean> patterns) {
		if(patterns == null) {
			patterns = new HashMap<String, Boolean>();
		}
		
		for( THREE_CONTINUATION_PATTERN p : THREE_CONTINUATION_PATTERN.values()) {
			Method ismethod = null;
			try {
				
				ismethod = ThreeContinuationPatternDetector.class.getDeclaredMethod(p.getMethodName());
				patterns.put(p.getMethodName(), (Boolean) ismethod.invoke(null));
				
			} catch (NoSuchMethodException | SecurityException |
					IllegalAccessException | IllegalArgumentException |
					InvocationTargetException  e) {
				e.printStackTrace();
			}

		}
		
		return patterns;
	}
	
	public static Boolean upsideGapThreeBullish() {
		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
		
		return (one.isTrend(TYPE_TREND.UPPER) && one.isBull() && two.isBull() && !three.isBull() && 
				   (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
				   && (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
				   two.getOpen()>one.getClose() &&
				   three.getOpen()>two.getOpen() && three.getOpen()<two.getClose() && three.getClose()<one.getClose());
	}
	
	public static Boolean upsideGapThreeBearish() {
		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
		return (one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && !two.isBull() && three.isBull() && 
				   (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
				   && (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
				   two.getOpen()<one.getClose() &&
				   three.getOpen()<two.getOpen() && three.getOpen()>two.getClose() && three.getClose()>one.getClose());
	}
	
	
	public static Boolean upsideTatsukiGapBullish() {
		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
		return (one.isTrend(TYPE_TREND.UPPER) && one.isBull() && two.isBull() && !three.isBull() && 
				   !one.isType(TYPE_CANDLESTICK.CAND_DOJI) && !two.isType(TYPE_CANDLESTICK.CAND_DOJI) && 
				   two.getOpen()>one.getClose() && 
				   three.getOpen()>two.getOpen() && three.getOpen()<two.getClose() && three.getClose()<two.getOpen() 
				   && three.getClose()>one.getClose());
	}
	
	public static Boolean upsideTatsukiGapBearish() {
		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
		return (one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && !two.isBull() && three.isBull() && 
				   !one.isType(TYPE_CANDLESTICK.CAND_DOJI) && !two.isType(TYPE_CANDLESTICK.CAND_DOJI) && 
				   two.getOpen()<one.getClose() &&
				   three.getOpen()<two.getOpen() && three.getOpen()>two.getClose() && three.getClose()>two.getOpen() 
				   && three.getClose()<one.getClose());
	}

}
