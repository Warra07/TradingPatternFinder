package com.example.candle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.*;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;

import com.example.candlecore.CandleQueue;
import com.example.candlecore.Candlestick;
import com.example.candlecore.TYPE_TREND;
import com.example.continuouspatterndetector.FourContinuationPatternDetector;
import com.example.continuouspatterndetector.ThreeContinuationPatternDetector;
import com.example.continuouspatterndetector.TwoContinuationPatternDetector;
import com.example.reversalpatterndetector.FiveReversalPatternDetector;
import com.example.reversalpatterndetector.FourReversalPatternDetector;
import com.example.reversalpatterndetector.MultipleReversalPatternDetector;
import com.example.reversalpatterndetector.OneReversalPatternDetector;
import com.example.reversalpatterndetector.TwoReversalPatternDetector;
import com.example.reversalpatterndetector.ThreeReversalPatternDetector;

// packages for technical indicators
import org.patriques.TechnicalIndicators;
import org.patriques.input.technicalindicators.*;
import org.patriques.output.technicalindicators.*;
import org.patriques.output.technicalindicators.data.IndicatorData;

import com.example.indicators.*;

@SpringBootApplication
public class TradingPatternFinderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TradingPatternFinderApplication.class, args);
	    String apiKey = "L2XQBCB0BV31ZGF1";
	    int timeout = 3000;
	    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
	    TimeSeries stockTimeSeries = new TimeSeries(apiConnector);
	    
	    // api for technical indicators
	    TechnicalIndicators technicalIndicators = new TechnicalIndicators(apiConnector);
	    
	    try {
	    	// Due to same name issue between org.patriques.input.timeseries.Interval and org.patriques.input.technicalindicators.Interval, 
	    	// I recommend we use full name of the package
	    	IntraDay response = stockTimeSeries.intraDay("MSFT", org.patriques.input.timeseries.Interval.ONE_MIN, OutputSize.COMPACT);
	    	//Daily response = stockTimeSeries.daily("MSFT", OutputSize.COMPACT);
	    	Map<String, String> metaData = response.getMetaData();
	    	System.out.println("Information: " + metaData.get("1. Information"));
	    	System.out.println("Stock: " + metaData.get("2. Symbol"));
	    	
	    	List<StockData> stockData = response.getStockData();
	    	stockData.forEach(stock -> {
	    		Candlestick test = new  Candlestick( stock.getOpen(), stock.getHigh(), stock.getLow(), stock.getClose(), stock.getDateTime(),
	    				CandleQueue.getTrendFromMa(stock.getClose(), 5));
	    		CandleQueue.addCandlestick(test);
	    		//System.out.println(test);
	    		
	    		HashMap<String, Boolean> patterns = OneReversalPatternDetector.patternReversalCheking(null);
	    		if(CandleQueue.getQueueSize() > 1) {
	    			TwoReversalPatternDetector.patternRevesalChecking(patterns);
	    			TwoContinuationPatternDetector.patternReversalCheking(patterns);
	    			}
	    		if (CandleQueue.getQueueSize() > 2) {
	    			ThreeReversalPatternDetector.patternReversalCheking(patterns);
	    			ThreeContinuationPatternDetector.patternReversalCheking(patterns); 
	    			}
	    		if (CandleQueue.getQueueSize() > 3) {
	    			FourReversalPatternDetector.patternReversalCheking(patterns);
	    			FourContinuationPatternDetector.patternReversalCheking(patterns);
	    			}
	    		if (CandleQueue.getQueueSize() > 4) {
	    			FiveReversalPatternDetector.patternReversalCheking(patterns);
	    			}
	    		MultipleReversalPatternDetector.patternReversalCheking(patterns);
	    		
	    		for ( Map.Entry<String, Boolean> entry : patterns.entrySet()) {
	    			String key = entry.getKey();
	    			Boolean tab = entry.getValue();
	    			
	    			if(tab) System.out.println(key);
	    			// do something with key and/or tab
	    			}
	    		});
	    	// Interface to technical indicators
	    	IndicatorHandler indicatorHandler = new IndicatorHandler();
	    	String stockCode = "MSFT"; // Microsoft
	    	
	    	// ---------------------------------------------------------------------------------------
	    	// RSI Query: 14-days(period) close-price daily RSI
	    	RSI response_RSI_long = technicalIndicators.rsi(stockCode, org.patriques.input.technicalindicators.Interval.DAILY, TimePeriod.of(10), SeriesType.CLOSE);
	    	RSI response_RSI_short = technicalIndicators.rsi(stockCode, org.patriques.input.technicalindicators.Interval.DAILY, TimePeriod.of(5), SeriesType.CLOSE);
	    	
	    	// Analyse recent 30 days
	    	int size_result = 30;
	    	
	    	// RSI result of day i consists of following booleans : TrendUpLong, TrendUpShort, TrendDownLong, 
	    	// TrendDownShort, OverboughtLong, OverboughtShort, OversoldLong, OversoldShort, ShortUpCrossLong, ShortDownCrossLong
	    	List<RSI_PatternData> rsiResult = indicatorHandler.RSI_IndicatorHander(response_RSI_short, response_RSI_long, size_result);
	    	// e.g. rsiResult.get(0).isTrendUpLong() returns true if the last trading day's long-term trend goes up.
	    	
	    	// Visualize result: 3 days RSI
	    	for (int i = 0; i < 3; i++) {
	    		RSI_PatternData j0 = rsiResult.get(i); 
	    		System.out.println(j0.getDatetime()+" Long:"+j0.getRsiLong()+" Short:"+j0.getRsiShort());
	    		System.out.println("TrendUpLong, TrendDownLong, TrendUpShort, TrendDownShort, "
	    				+ "OverboughtLong, OverboughtShort, OversoldLong, OversoldShort, ShortUpCrossLong, ShortDownCrossLong");
	    		System.out.println(j0.isTrendUpLong()+"         "+j0.isTrendDownLong()+"           "
	    				+ j0.isTrendUpShort()+"          "+j0.isTrendDownShort()+"           "+j0.isOverboughtLong()
	    				+ "           "+j0.isOverboughtShort()+"           "+j0.isOversoldLong()+"           "+j0.isOversoldShort()
	    				+ "           "+j0.isShortUpCrossLong()+"             "+j0.isShortDownCrossLong());
	    		}
	    	
	    	// ---------------------------------------------------------------------------------------
	    	// STOCH Query
	    	STOCHF response_STOCH_fast = technicalIndicators.stochf(stockCode, org.patriques.input.technicalindicators.Interval.DAILY, 
	    			FastKPeriod.of(14), FastDPeriod.of(3), FastDMaType.SMA);
	    	STOCH response_STOCH_slow = technicalIndicators.stoch(stockCode, org.patriques.input.technicalindicators.Interval.DAILY, 
	    			FastKPeriod.of(14), SlowKPeriod.of(3), SlowDPeriod.of(3), SlowKMaType.SMA, SlowDMaType.SMA);
	    	
	    	// STOCH result of day i consists of following booleans : Overbought, Oversold, 
	    	// fastKDownCrossfastD, slowKDownCrossSlowD, fastKUpCrossfastD, slowKUpCrossslowD
	    	List<STOCH_PatternData> stochResult = indicatorHandler.STOCH_IndicatorHandler(response_STOCH_fast, response_STOCH_slow, size_result);
	    	// e.g. stochResult.get(0).isOversold() returns true if the last trading day's STOCH < 20.
	    	
	    	// Visualize result: 3 days STOCH
	    	for (int i = 0; i < 3; i++) {
	    		STOCH_PatternData j0 = stochResult.get(i);
	    		System.out.println(j0.getDatetime()+" fastK:"+j0.getFastK()+" fastD/slowK:"+j0.getSlowK()+" slowD:"+j0.getSlowD());
	    		System.out.println("Overbought, Oversold, fastKDownCrossfastD, slowKDownCrossSlowD, fastKUpCrossfastD, slowKUpCrossslowD");
	    		System.out.println(j0.isOverbought()+"        "+j0.isOversold()+"       "+j0.isFastKDownCrossfastD()+"                 "
	    				+j0.isSlowKDownCrossSlowD()+"                 "+j0.isFastKUpCrossfastD()+"               "+j0.isSlowKUpCrossslowD());
	    	}
	    	
	    	/*
	    	// ---------------------------------------------------------------------------------------
	    	// Williams Query
	    	WILLR response_WILLR = technicalIndicators.willr(stockCode, org.patriques.input.technicalindicators.Interval.DAILY, TimePeriod.of(14));
	    	List<WILLR_PatternData> willrResult = indicatorHandler.WILLR_IndicatorHandler(response_WILLR, size_result);
	    	
	    	// Visualize result: 3 days Williams %R
	    	for (int i = 0; i < 3; i++) {
	    		WILLR_PatternData j0 = willrResult.get(i);
	    		System.out.println(j0.getDatetime()+" "+j0.getWillr()+" overbought:"+j0.isOverbought()+" oversold:"+j0.isOversold());
	    	}
	    	*/
	    	
	    	} catch (AlphaVantageException e) {
	    		System.out.println("something went wrong");
	    	}
	    }
}