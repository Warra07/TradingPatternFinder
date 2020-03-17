package com.example.candle;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class TradingPatternFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingPatternFinderApplication.class, args);
	    String apiKey = "GGKBONYCY3BTIB5J";
	    int timeout = 3000;
	    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
	    TimeSeries stockTimeSeries = new TimeSeries(apiConnector);
	    
	    try {
	      IntraDay response = stockTimeSeries.intraDay("MSFT", Interval.ONE_MIN, OutputSize.COMPACT);
	      Map<String, String> metaData = response.getMetaData();
	      System.out.println("Information: " + metaData.get("1. Information"));
	      System.out.println("Stock: " + metaData.get("2. Symbol"));
	      
	      List<StockData> stockData = response.getStockData();
	      stockData.forEach(stock -> {
	    	  Candlestick test = new  Candlestick( stock.getOpen(), stock.getHigh(), stock.getLow(), stock.getClose(), stock.getDateTime(),
	    			  CandleQueue.getTrendFromMa(stock.getClose(), 5));
	    	  CandleQueue.addCandlestick(test);
	    	  System.out.println(test);
	    	  
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
	    		    if(tab)
	    		    System.out.println(key);
	    		    // do something with key and/or tab
	    		}
	      });
	    } catch (AlphaVantageException e) {
	      System.out.println("something went wrong");
	    }
	}

}
