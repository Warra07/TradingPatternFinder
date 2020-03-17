package com.example.candlecore;

import org.apache.commons.collections4.queue.CircularFifoQueue;

public abstract class CandleQueue {
	private static CircularFifoQueue<Candlestick> candlelist = null;
	private static int size = 10;
	private static double average_body = 0;

	private static CircularFifoQueue<Candlestick> getInstance() {
		if (candlelist == null) {
			candlelist = new CircularFifoQueue<Candlestick>(size);
			
		}
		return candlelist;
	}
	public static void addCandlestick(Candlestick cs) {
		CandleQueue.getInstance().add(cs);
		CandleQueue.countAverageBody();
	}
	public static int getQueueSize() {
		return CandleQueue.getInstance().size();
	}
	public static Candlestick getCandle(int index) {
		return CandleQueue.getInstance().get(index);
	}
	
	private static void countAverageBody() {
		CandleQueue.average_body = 0;
		int size = candlelist.size();
		for(int i = 0; i < size; i++) {
			CandleQueue.average_body += candlelist.get(i).getBodysize(); 
		}
		CandleQueue.average_body /= size;
	}
	
	public static double getAverageBody() {
		return CandleQueue.average_body;
	}
	
	public static double movingAverage(int size) {
		double ma = 0;
		for(int i = 0; i < size; i++) {
			if(i < CandleQueue.getQueueSize()) {
			ma += candlelist.get(i).getClose(); 
			} else {
				break;
			}
		}
		
		ma /= size;
		
		return ma;
	}
	
	public static TYPE_TREND getTrendFromMa(double close, int ma_window) {
		double ma = movingAverage(ma_window);
		
		if(ma< close) return TYPE_TREND.UPPER;
		 if(ma>close) return TYPE_TREND.DOWN;
		 
		 return TYPE_TREND.LATERAL;
		   
	}
}
