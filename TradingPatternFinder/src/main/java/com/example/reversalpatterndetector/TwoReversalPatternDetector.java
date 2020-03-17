package com.example.reversalpatterndetector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.example.candlecore.CandleQueue;
import com.example.candlecore.Candlestick;
import com.example.candlecore.CandlestickParameters;
import com.example.candlecore.TYPE_CANDLESTICK;
import com.example.candlecore.TYPE_TREND;

public abstract class TwoReversalPatternDetector {
	public static HashMap<String,Boolean> patternRevesalChecking(HashMap<String, Boolean> patterns) {
		if(patterns == null) {
			patterns = new HashMap<String, Boolean>();
		}
		
		for( TWO_REVERSAL_PATTERN p : TWO_REVERSAL_PATTERN.values()) {
			Method ismethod = null;
			try {
				
				ismethod = TwoReversalPatternDetector.class.getDeclaredMethod(p.getMethodName());
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
	

	public static Boolean engulfingBullish() {
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isTrend(TYPE_TREND.DOWN) && 
	    		  two.isBull() && one.getBodysize() < two.getBodysize()) 
	        {
	            if(one.getClose() > two.getOpen() && one.getOpen() < two.getClose()) 
	              {
	            	return true;
	              }
	        }
	      return false;
	}

	

	public static Boolean engulfingBearish() {
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.UPPER) && one.isBull() && two.isTrend(TYPE_TREND.UPPER) && 
	    		  !two.isBull() && one.getBodysize() < two.getBodysize()) 
	        {
	            if(one.getClose() < two.getOpen() && one.getOpen() > two.getClose()) 
	              {
	            	return true;
	              }
	        }
	      return false;
	}

	public static Boolean haramiCrossBullish() {
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);

	      if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && 
	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && two.isType(TYPE_CANDLESTICK.CAND_DOJI)) 
	        {
	            if(one.getClose() < two.getOpen() && one.getClose() < two.getClose() && one.getOpen() > two.getClose()) // doji inside the body of the first candlestick
	              {
	            	return true;
	              }
	        }
	      return false;
	}
	
	public static Boolean haramiCrossBearish() {
		
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);

	      if(one.isTrend(TYPE_TREND.UPPER) && one.isBull() && 
	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && two.isType(TYPE_CANDLESTICK.CAND_DOJI)) 
	        {
	            if(one.getClose() > two.getOpen() && one.getClose() > two.getClose() && one.getOpen() < two.getClose()) 
	              {
	            	return true;
	              }
	        }
	      return false;
	}
	
	public static Boolean haramiBullish() {
		
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isBull() &&
	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) &&  
	          !two.isType(TYPE_CANDLESTICK.CAND_DOJI) && one.getBodysize() > two.getBodysize()) 
	        {
	            if(one.getClose() < two.getOpen() &&
	            		one.getClose() < two.getClose() && one.getOpen() > two.getClose())
	              {
	            	return true;
	              }
	           
	        }
	      return false;
	}
	
	public static Boolean haramiBearish() {
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.UPPER) && one.isBull() && !two.isBull() &&
	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) &&  
	          !two.isType(TYPE_CANDLESTICK.CAND_DOJI) && one.getBodysize() > two.getBodysize()) 
	        {
	            if(one.getClose() > two.getOpen() &&
	            		one.getClose() > two.getClose() && one.getOpen() < two.getClose())
	              {
	            	return true;
	              }
	           
	        }
	      return false;
	}

	public static Boolean dojiStarBullish() {
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);		
		
	      if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && 
	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) &&
	         two.isType(TYPE_CANDLESTICK.CAND_DOJI)) 
	        {
	            if(one.getClose() > two.getOpen() && one.getClose() > two.getClose()) 
	              {
	            	return true;
	              }
	        }
	      return false;   
	}
	
	public static Boolean dojiStarBearish() {
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);		
		
	      if(one.isTrend(TYPE_TREND.UPPER) && one.isBull() && 
	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) &&
	         two.isType(TYPE_CANDLESTICK.CAND_DOJI)) 
	        {
	            if(one.getClose() < two.getOpen() && one.getClose() < two.getClose()) 
	              {
	            	return true;
	              }
	        }
	      return false;	
	}
	
	public static Boolean pearcingPattern() {
		

		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);	
		
        if(one.isTrend(TYPE_TREND.DOWN)  &&  !one.isBull()  &&  two.isTrend(TYPE_TREND.DOWN)  &&  two.isBull() && 
        		(one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG))
        		&& (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
           two.getClose() > (one.getClose() + one.getOpen())/2)
          {
              if(two.getOpen() < one.getLow() && two.getClose() <= one.getOpen())
                {
            	  return true;
                }
             
          }
        return false;
	}
	
	public static Boolean darkCloudCover() {

		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);	

    
  if(one.isTrend(TYPE_TREND.UPPER)  &&  one.isBull()  &&  two.isTrend(TYPE_TREND.UPPER)  &&  !two.isBull() && 
		  (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
		  two.getClose() > (one.getClose() + one.getOpen())/2)
    {
        if(one.getHigh() < two.getOpen() && two.getClose() >= one.getOpen())
          {
        	return true;
          }
       
    }
  	return false;
		
	}
	
	public static Boolean meetingLinesBullish() {

		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);	

	        return (one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isTrend(TYPE_TREND.DOWN) && two.isBull() &&
	        		(one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG))
	        		&& (one.getClose() >= two.getClose() - CandlestickParameters.MEETING_LINE_VAR*two.getBodysize() )
	        		&& (one.getClose() <= two.getClose() + CandlestickParameters.MEETING_LINE_VAR*two.getBodysize() )
	        		&& one.getBodysize() < two.getBodysize() && one.getLow() > two.getOpen());

	}
	
	public static Boolean meetingLinesBearish() {
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
		return (one.isTrend(TYPE_TREND.UPPER) && one.isBull() && two.isTrend(TYPE_TREND.UPPER) && !two.isBull() &&
        		(one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG))
        		&& (one.getClose() >= two.getClose() - CandlestickParameters.MEETING_LINE_VAR*two.getBodysize() )
        		&& (one.getClose() <= two.getClose() + CandlestickParameters.MEETING_LINE_VAR*two.getBodysize() )
        		&& one.getBodysize() < two.getBodysize() && one.getHigh() < two.getOpen());
		
	}

	public static Boolean matchingLow() {
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
	      return (one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isTrend(TYPE_TREND.DOWN) && !two.isBull()
	        && (one.getClose() >= two.getClose() - CandlestickParameters.MEETING_LINE_VAR*two.getBodysize() )
	        && (one.getClose() <= two.getClose() + CandlestickParameters.MEETING_LINE_VAR*two.getBodysize() )
	        && one.getBodysize() > two.getBodysize());

	}
	
	public static Boolean homingPigeon() {
		
		Candlestick one = CandleQueue.getCandle(1);
		Candlestick two = CandleQueue.getCandle(0);
		
	      return (one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isTrend(TYPE_TREND.DOWN) && !two.isBull() && // check the trend direction and the candlestick direction
	    		  (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG))
	    		  && one.getClose() < two.getClose() && one.getOpen() > two.getOpen());

	}

}
