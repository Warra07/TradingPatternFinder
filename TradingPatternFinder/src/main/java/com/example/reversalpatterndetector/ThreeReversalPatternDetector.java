package com.example.reversalpatterndetector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.example.candlecore.CandleQueue;
import com.example.candlecore.Candlestick;
import com.example.candlecore.TYPE_CANDLESTICK;
import com.example.candlecore.TYPE_TREND;

public abstract class ThreeReversalPatternDetector {
	
	
	public static HashMap<String,Boolean> patternReversalCheking(HashMap<String, Boolean> patterns) {
		if(patterns == null) {
			patterns = new HashMap<String, Boolean>();
		}
		
		for( THREE_REVERSAL_PATTERN p : THREE_REVERSAL_PATTERN.values()) {
			Method ismethod = null;
			try {
				
				ismethod = ThreeReversalPatternDetector.class.getDeclaredMethod(p.getMethodName());
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
	
	
	public static Boolean abandonedBabyBullish() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      if (one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && three.isTrend(TYPE_TREND.DOWN) && three.isBull() && 
	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
	         && (three.isType(TYPE_CANDLESTICK.CAND_LONG) || three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
	         two.isType(TYPE_CANDLESTICK.CAND_DOJI) && 
	         three.getClose() < one.getOpen() && three.getClose() > one.getClose()) 
	        {

	            if(one.getLow() > two.getHigh()  && three.getLow() > two.getHigh())
	              {
	            	return true;
	              }
	        }
	      return false;
	}
	
	public static Boolean morningStar() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && three.isTrend(TYPE_TREND.DOWN) && three.isBull() && 
	 	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
		         && (three.isType(TYPE_CANDLESTICK.CAND_LONG) || three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
		         two.isType(TYPE_CANDLESTICK.CAND_SHORT) && 
		         three.getClose() < one.getOpen() && three.getClose() > one.getClose()) 
	    	        {

	    	            if(two.getOpen()<one.getClose() && two.getClose()<one.getClose()) 
	    	            {
	    	            	return true;
	    	             }
	    	           
	    	        }
	      return false;
		
	}
	
	public static Boolean abandonedBabyBearish() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      // The Abandoned Baby, the bearish model
	      if(one.isTrend(TYPE_TREND.UPPER) && one.isBull() && three.isTrend(TYPE_TREND.UPPER) && !three.isBull() && 
	    	(one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
	 	    && (three.isType(TYPE_CANDLESTICK.CAND_LONG) || three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
	 	    two.isType(TYPE_CANDLESTICK.CAND_DOJI) && 
	 	    three.getClose() > one.getOpen() && three.getClose() < one.getClose()) 
	        {

	            if(one.getHigh()<two.getLow() && three.getHigh()<two.getLow()) 
	            {
	            	return true;
	              }
	           
	        }
	      return false;
		
	}

	
	public static Boolean eveningStar() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      if (one.isTrend(TYPE_TREND.UPPER) && one.isBull() && three.isTrend(TYPE_TREND.UPPER) && !three.isBull() && 
	  	    	(one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
		 	    && (three.isType(TYPE_CANDLESTICK.CAND_LONG) || three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
		 	    two.isType(TYPE_CANDLESTICK.CAND_SHORT) && 
		 	    three.getClose() > one.getOpen() && three.getClose() < one.getClose()) 
	        {
	            if(two.getOpen()>one.getClose() && two.getClose()>one.getClose())
	            {

	            	return true;
	            }
	           
	        }
	      return false;
	}
		
	
	public static Boolean morningDojiStar() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      
	      if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && three.isTrend(TYPE_TREND.DOWN) && three.isBull() && 
		 	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
			         && (three.isType(TYPE_CANDLESTICK.CAND_LONG) || three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
			         two.isType(TYPE_CANDLESTICK.CAND_DOJI) && 
			         three.getClose() < one.getOpen() && three.getClose() > one.getClose()) 
	      {
	    	            if(two.getOpen()<one.getClose()) 
	    	              {
	    	            	return true;
	    	               }
	    	           
	     }
	      return false;
		
	}
	
	public static Boolean eveningDojiStar() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.UPPER) && one.isBull() && three.isTrend(TYPE_TREND.UPPER) && !three.isBull() && 
		  	    	(one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
			 	    && (three.isType(TYPE_CANDLESTICK.CAND_LONG) || three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
			 	    two.isType(TYPE_CANDLESTICK.CAND_DOJI) && 
			 	    three.getClose() > one.getOpen() && three.getClose() < one.getClose()) 
	      {

	    	            if(two.getOpen()>one.getClose()) 
	    	              {
	    	            	return true;
	    	              }
	      }
	      return false;
		
	}
	
	public static Boolean bearishUpsideGapTwoCrows() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      return (one.isTrend(TYPE_TREND.UPPER) && one.isBull() && two.isTrend(TYPE_TREND.UPPER) 
	    	&& !two.isBull() && three.isTrend(TYPE_TREND.UPPER) && !three.isBull() && 
	    	(one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG))
	    	&& one.getClose()<two.getClose() && one.getClose()<three.getClose() && 
	         two.getOpen()<three.getOpen() && two.getClose()>three.getClose());

		
	}

	public static Boolean twoCrows() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      return (one.isTrend(TYPE_TREND.UPPER) && one.isBull() && two.isTrend(TYPE_TREND.UPPER) 
	  	    	&& !two.isBull() && three.isTrend(TYPE_TREND.UPPER) && !three.isBull() && 
	  	    	(one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) 
			 && (three.isType(TYPE_CANDLESTICK.CAND_LONG) || three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
	    	         one.getClose()<two.getClose() && 
	    	         three.getOpen()>two.getClose() &&
	    	        three.getClose()<one.getClose());

	}

	public static Boolean threeStarInTheSouth() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      if( one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && !two.isBull() && !three.isBull() && //check the trend direction and direction of the candlestick
	    		  (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && (three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU) || three.isType(TYPE_CANDLESTICK.CAND_SHORT)) &&
	    	         one.getBodysize()>two.getBodysize() && one.getLow()<two.getLow() && three.getLow()>two.getLow() && three.getHigh()<two.getHigh())
	    	        {
	    	            if(one.getClose()<two.getOpen() && two.getClose()<three.getOpen()) 
	    	              {
	    	            	return true;

	    	              }
	    	           
	    	        }
	      return false;
		
	}
	
	public static Boolean deliberation() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.UPPER) && one.isBull() && two.isBull() && three.isBull() && 
	    		  (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
	    		  (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
	    	         (three.isType(TYPE_CANDLESTICK.CAND_SPIN_TOP) || three.isType(TYPE_CANDLESTICK.CAND_SHORT))) 
	      {

	    	            if(one.getClose()>two.getOpen() && two.getClose()<=three.getOpen()) 

	    	              {
	    	            	return true;
	    	              }

	     }
	      return false;
		
	}
	
	public static Boolean threeWhiteSoldiers() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.DOWN) && one.isBull() && two.isBull() && three.isBull() && 
	    		  (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
	    		  (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) &&  
	    		  (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)))  
	    		  {
	    	            if(one.getClose()>two.getOpen() && two.getClose()>three.getOpen())
	    	            {
	    	            	return true;
	    	              }
	    	           
	    	        }
	      return false;
	}
	
	public static Boolean threeBlackCrows() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
		return (one.isTrend(TYPE_TREND.UPPER) && !one.isBull() && !two.isBull() && !three.isBull() && 
	    		  (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
	    		  (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) &&  
	    		  (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) &&
	    		  one.getClose()<two.getOpen() && two.getClose()<three.getOpen());
	}
	
	public static Boolean threeOutsideUp() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && two.isTrend(TYPE_TREND.DOWN) &&
	    		  two.isBull() && three.isBull() && 
	         two.getBodysize()>one.getBodysize() && 
	         three.getClose()>two.getClose()) 
	        {

	            if(one.getClose()>two.getOpen() && one.getOpen()<two.getClose())
	              {
	            	return true;
	              }
	           
	        }
	      return false;
		
	}
	
	public static Boolean threeOutsideDown() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.UPPER) && one.isBull() && two.isTrend(TYPE_TREND.UPPER) &&
	    		  !two.isBull() && !three.isBull() && two.getBodysize()>one.getBodysize() &&
	    		  three.getClose()<two.getClose()) 
	    	        {
	    	            if(one.getClose()<two.getOpen()&& one.getOpen()>two.getClose()) 
	    	            {
	    	            	return true;
	    	            	
	    	              }
	    	           
	    	        }
	      return false;
	}
	
	public static Boolean threeInsideUp() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);

	      if(one.isTrend(TYPE_TREND.DOWN)  &&  !one.isBull()  &&  two.isBull() && three.isBull() && 
	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) &&  
	         one.getBodysize()>two.getBodysize() && 
	         three.getClose()>two.getClose()) 
	        {
	            if(one.getClose()<two.getOpen() && one.getClose()<two.getClose() && one.getOpen()>two.getClose()) 
	              {
	            	return true;
	              }
	           
	        }
	      return false;
	}

	public static Boolean threeInsideDown() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		

	      if(one.isTrend(TYPE_TREND.UPPER) && one.isBull() && !two.isBull() && !three.isBull() &&
	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
	         one.getBodysize()>two.getBodysize() && 
	         three.getClose()<two.getClose()) 
	        {

	            if(one.getClose()>two.getOpen() && one.getClose()>two.getClose() && one.getOpen()<two.getClose()) 
	              {
	            	return true;
	              }
	           
	        }
	      return false;
		
	}
	
	public static Boolean threeStarsBullish() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		if(one.isTrend(TYPE_TREND.DOWN)  && // check the trend direction
				   one.isType(TYPE_CANDLESTICK.CAND_DOJI) && two.isType(TYPE_CANDLESTICK.CAND_DOJI) && three.isType(TYPE_CANDLESTICK.CAND_DOJI))
				  {

				      if(two.getOpen()!=one.getClose() && two.getClose()!=three.getOpen()) 
				        {
				    	  return true;
				        }
				     
				  }
		return false;
	}
	
	

	public static Boolean threeStarsBearish() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);

		if(one.isTrend(TYPE_TREND.UPPER) && // check the trend direction
		   one.isType(TYPE_CANDLESTICK.CAND_DOJI) && two.isType(TYPE_CANDLESTICK.CAND_DOJI) && three.isType(TYPE_CANDLESTICK.CAND_DOJI)) 
		  {
		      if(two.getOpen()!=one.getClose() && two.getClose()!=three.getOpen())
		      {
		    	  return true;
		        }
		     
		  }
		return false;

	}
	
	public static Boolean identicalThreeCrows() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      if(one.isTrend(TYPE_TREND.UPPER) && !one.isBull() && !two.isBull() && !three.isBull() && 
	    	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && (two.isType(TYPE_CANDLESTICK.CAND_LONG) || two.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && 
	    	         (three.isType(TYPE_CANDLESTICK.CAND_LONG) || three.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG))) 
	    	        {

	    	            if(one.getClose()>=two.getOpen() && two.getClose()>=three.getOpen()) 
	    	            {
	    	            	return true;
	    	              }
	    	           
	    	        }
	      return false;
	}
	
	public static Boolean uniqueThreeRiverBottom() {

		Candlestick one = CandleQueue.getCandle(2);
		Candlestick two = CandleQueue.getCandle(1);
		Candlestick three = CandleQueue.getCandle(0);
		
	      return (one.isTrend(TYPE_TREND.DOWN) && !one.isBull() && !two.isBull() && three.isBull() && 
	    	         (one.isType(TYPE_CANDLESTICK.CAND_LONG) || one.isType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG)) && three.isType(TYPE_CANDLESTICK.CAND_SHORT) && 
	    	         two.getOpen()<one.getOpen() && two.getClose()>one.getClose() && two.getLow()<one.getLow() && 
	    	         three.getClose()<two.getClose());
	}


}
