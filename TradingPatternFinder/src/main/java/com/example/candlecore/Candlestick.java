package com.example.candlecore;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.lang.Math; 

public class Candlestick {
	   double            open,high,low,close; 
	   LocalDateTime          time;     
	   TYPE_TREND       trend;    
	   boolean              bull;     
	   double            bodysize; 
	   HashMap<TYPE_CANDLESTICK, Boolean> type = new HashMap<TYPE_CANDLESTICK, Boolean>();
	   
	   
		
		
	
	@Override
	public String toString() {
		return "Candlestick [open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", time=" + time
				+ ", trend=" + trend + ", bull=" + bull + ", bodysize=" + bodysize + ", type=" + type + "]";
	}


	public Candlestick(double open, double high, double low, double close, LocalDateTime time, TYPE_TREND trend) {
		super();
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.time = time;
		this.bull =  this.open < this.close;
		this.bodysize = Math.abs(this.open - this.close);
		this.presetType();
		this.findType(CandleQueue.getAverageBody());
		this.trend = trend;
	}
	
	
	public boolean isTrend(TYPE_TREND trn) {
		return trend == trn;
	}
	private void presetType() {
		for (TYPE_CANDLESTICK t : TYPE_CANDLESTICK.values()) { 
		    type.put(t, false);
		}
	}

	private void findType(double averageBody) {
		// Bougie longue
	  	 if(this.getBodysize() > averageBody*CandlestickParameters.LONG_CANDLE_VAR)
			{ this.setType(TYPE_CANDLESTICK.CAND_LONG, true); }
		// Bougie courte
		 if(this.getBodysize() < averageBody*CandlestickParameters.SHORT_CANDLE_VAR)
			{ this.setType(TYPE_CANDLESTICK.CAND_SHORT, true); }
		// Bougie Doji
		 if(this.getBodysize() < this.getHL()*CandlestickParameters.DOJI_CANDLE_VAR) 
		    { this.setType(TYPE_CANDLESTICK.CAND_DOJI, true); }
		// Bougie Maribozu
		 if((this.getShadeLow()<this.getBodysize()*CandlestickParameters.MARIBOZU_CANDLE_VAR 
				   || this.getShadeHigh()<this.getBodysize()*CandlestickParameters.MARIBOZU_CANDLE_VAR)
				   && this.getBodysize() > 0)
		     {
		      if(this.getType().get(TYPE_CANDLESTICK.CAND_LONG))
		      { this.setType(TYPE_CANDLESTICK.CAND_MARIBOZU_LONG, true); }
		      else
		         { this.setType(TYPE_CANDLESTICK.CAND_MARIBOZU, true); }
		     }
		   
		   // Bougie Hammer
		   if(this.getShadeLow() > this.getBodysize()*CandlestickParameters.HAMMER_CANDLE_LOWPART_VAR 
				   && this.getShadeHigh() < this.getBodysize()*CandlestickParameters.HAMMER_CANDLE_HIGHPART_VAR) 
			   { this.setType(TYPE_CANDLESTICK.CAND_HAMMER, true); }
		   
		   // Bougie inverted Hammer
		   if(this.getShadeLow()<this.getBodysize()*CandlestickParameters.HAMMER_CANDLE_HIGHPART_VAR 
				   && this.getShadeHigh()>this.getBodysize()*CandlestickParameters.HAMMER_CANDLE_LOWPART_VAR)
			   { this.setType(TYPE_CANDLESTICK.CAND_INVERT_HAMMER, true); }
		   
		   // Bougie Spinning Top
		   if(this.getType().get(TYPE_CANDLESTICK.CAND_SHORT) 
				   && this.getShadeLow()>this.getBodysize() 
				   && this.getShadeHigh()>this.getBodysize()) 
			   { this.setType(TYPE_CANDLESTICK.CAND_SPIN_TOP, true); }



	}


	public Boolean isType(TYPE_CANDLESTICK tp) {
		return this.getType().get(tp);
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public TYPE_TREND getTrend() {
		return trend;
	}

	public void setTrend(TYPE_TREND trend) {
		this.trend = trend;
	}
	public boolean isBull() {
		return bull;
	}
	public void setBull(boolean bull) {
		this.bull = bull;
	}
	public double getBodysize() {
		return bodysize;
	}
	public void setBodysize(double bodysize) {
		this.bodysize = bodysize;
	}
	public HashMap<TYPE_CANDLESTICK, Boolean> getType() {
		return type;
	}
	public void setType(TYPE_CANDLESTICK type, boolean istype) {
		this.type.put(type, istype);
	}
	
	public double getShadeHigh( ) {
		if(this.bull)
			return this.high - this.close;
		return this.high - this.open;
	}
	
	public double getShadeLow( ) {
		if(this.bull)
			return this.open - this.low;
		return this.close - this.low;
	}
	
	public double getHL() {
		return this.high - this.low;
	}

}
