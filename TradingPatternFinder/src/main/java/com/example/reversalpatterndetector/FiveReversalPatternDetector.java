package com.example.reversalpatterndetector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.example.candlecore.CandleQueue;
import com.example.candlecore.Candlestick;
import com.example.candlecore.TYPE_CANDLESTICK;
import com.example.candlecore.TYPE_TREND;

public abstract class FiveReversalPatternDetector {

	public static HashMap<String,Boolean> patternReversalCheking(HashMap<String, Boolean> patterns) {
		if(patterns == null) {
			patterns = new HashMap<String, Boolean>();
		}
		
		for( FIVE_REVERSAL_PATTERN p : FIVE_REVERSAL_PATTERN.values()) {
			Method ismethod = null;
			try {
				
				ismethod = FiveReversalPatternDetector.class.getDeclaredMethod(p.getMethodName());
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
	
	public static Boolean breakawayBullish() {
		Candlestick one = CandleQueue.getCandle(4);
		Candlestick two = CandleQueue.getCandle(3);
		Candlestick three = CandleQueue.getCandle(2);
		Candlestick four = CandleQueue.getCandle(1);
		Candlestick five = CandleQueue.getCandle(0);
		
		return (one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && !two.isBull() && !four.isBull() && five.isBull() && 
				   (one.isType(TYPE_CANDLESTICK.CAND_LONG)|| one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) &&  
				   two.isType(TYPE_CANDLESTICK.CAND_SHORT) && two.getOpen()<one.getClose() &&
				   three.isType(TYPE_CANDLESTICK.CAND_SHORT) && four.isType(TYPE_CANDLESTICK.CAND_SHORT) && 
				   (five.isType(TYPE_CANDLESTICK.CAND_LONG) || five.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
				   && five.getClose()<one.getClose() && five.getClose()>two.getOpen()); 
	}
	
	public static Boolean breakawayBearish() {
		Candlestick one = CandleQueue.getCandle(4);
		Candlestick two = CandleQueue.getCandle(3);
		Candlestick three = CandleQueue.getCandle(2);
		Candlestick four = CandleQueue.getCandle(1);
		Candlestick five = CandleQueue.getCandle(0);
		
		return (one.isTrend(TYPE_TREND.UPPER) && one.isBull() && two.isBull() && four.isBull() && !five.isBull() && 
				   (one.isType(TYPE_CANDLESTICK.CAND_LONG)|| one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) &&  
				   two.isType(TYPE_CANDLESTICK.CAND_SHORT) && two.getOpen()<one.getClose() && 
				   three.isType(TYPE_CANDLESTICK.CAND_SHORT) && four.isType(TYPE_CANDLESTICK.CAND_SHORT) && 
				   (five.isType(TYPE_CANDLESTICK.CAND_LONG) || five.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
				   && five.getClose()>one.getClose() && five.getClose()<two.getOpen());
	}
}
