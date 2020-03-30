package com.example.indicators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.patriques.output.technicalindicators.*;
import org.patriques.output.technicalindicators.data.IndicatorData;
import org.patriques.output.technicalindicators.data.STOCHDataFast;
import org.patriques.output.technicalindicators.data.STOCHDataSlow;
import org.patriques.output.technicalindicators.data.BBANDSData;

public class IndicatorHandler {

	/** START KB **/

	public List<BBANDS_PatternData> BBANDS_IndicatorHandler(List<BBANDSData> bbandsData, List<StockData> stockData, int size_result) { 
		
		List<BBANDS_PatternData> result = new ArrayList<BBANDS_PatternData>();
		
		// add List BBANDS Pattern value
		for (int i = 0; i < size_result; i++) {	
			if(bbandsData.get(i).getDateTime() == stockData.get(i).getDateTime()){
				BBANDS_PatternData tmp = new BBANDS_PatternData(bbandsData.get(i).getDateTime(), stockData.get(i).getClose(),
				bbandsData.get(i).getUpperBand(),bbandsData.get(i).getMiddleBand(),bbandsData.get(i).getLowerBand());
				result.add(tmp);
			}
		}
		
		// analyse BBANDS Data
		for (int i = 0; i < size_result; i++) {
			
						//Short term analysis

			BBANDS_PatternData bbands_PatternData = result.get(i); 
			
			// if BBANDS cross above upperbands, then overbought
			if(bbands_PatternData.CLOSEPRICE >= bbands_PatternData.UPPERBANDS){
				bbands_PatternData.OVERBOUGHT = true;
				bbands_PatternData.OVERSOLD = false;
			}
			
			// if BBANDS cross above lowerbands, then oversold
			if(bbands_PatternData.CLOSEPRICE <= bbands_PatternData.LOWERBANDS){
				bbands_PatternData.OVERSOLD = true;
				bbands_PatternData.OVERBOUGHT = false;
			}
			
		}	

		return result;
	}
	
	public List<CCI_PatternData> CCI_IndicatorHandler(List<IndicatorData> cci_data , int size_result, int nbDayForLongAnalysis) { 
			
		List<CCI_PatternData> result = new ArrayList<CCI_PatternData>();
		
		// add List CCI Pattern value
		for (int i = 0; i < size_result+nbDayForLongAnalysis; i++) {	
			CCI_PatternData tmp = new CCI_PatternData(cci_data.get(i).getDateTime(), cci_data.get(i).getData());
			result.add(tmp);
		}
		
		for (int i = 0; i < size_result; i++) {

			//Short term analysis
			CCI_PatternData cci_PatternData = result.get(i);
			if ( cci_PatternData.CCI_VALUE > 100 ) {
				cci_PatternData.OVERBOUGHT = true;
			}
			if ( cci_PatternData.CCI_VALUE < 100 ) {
				cci_PatternData.OVERSOLD = true;
			}
			
		}	
		
		result =  result.stream().limit(size_result).collect(Collectors.toList());
		return result;
	}

	public List<ATR_PatternData> ATR_IndicatorHandler(List<IndicatorData> atr_data , int size_result, int nbDayForLongAnalysis) { 
		
		List<ATR_PatternData> result = new ArrayList<ATR_PatternData>();
		double mean_atr = 0;
		double sum_atr = 0;
		for (int i = 0; i < atr_data.size(); i++) {	
			sum_atr = atr_data.get(i).getData();
		}
		mean_atr = sum_atr / atr_data.size();

		// add List ATR Pattern value
		for (int i = 0; i < size_result+nbDayForLongAnalysis; i++) {	
			ATR_PatternData tmp = new ATR_PatternData(atrData.get(i).getDateTime(), 
			atrData.get(i).getData(), mean_atr);
			result.add(tmp);
		}
		
		// analyse AROON
		for (int i = 0; i < size_result; i++) {
			
			ATR_PatternData atr = result.get(i); 
			
			if (atr.ATR_VALUE > mean_atr) {
				atr.STRENGTH_MOVE = true;
			}

		}

		result =  result.stream().limit(size_result).collect(Collectors.toList());
		return result;
	}

	public List<AROON_PatternData> AROON_IndicatorHandler(AROON response , int size_result) { 
		
		List<aroonData> aroonDatas = response.getData();
		
		List<AROON_PatternData> result = new ArrayList<AROON_PatternData>();
		
		// add List Aroon Pattern value
		for (int i = 0; i < size_result+2; i++) {	
			AROON_PatternData tmp = new AROON_PatternData(aroonDatas.get(i).getDateTime(), aroonDatas.get(i).getAroonUp(), aroonDatas.get(i).getAroonDown());
			result.add(tmp);
		}
		
		// analyse [size_result] days' AROON
		for (int i = 0; i < size_result; i++) {
			
			AROON_PatternData a1 = result.get(i); // day j
			AROON_PatternData a2 = result.get(i+1); // day j-1
			AROON_PatternData a3 = result.get(i+2); // day j-2
			
			// if AROONUP > 50, and AROONDOWN < 50,  then UPTREND
			if (a3.AroonUp() > 50 && a3.AroonDown() < 50) {
				if(a3.AroonUp() > 50 && a3.AroonDown() < 50){
					if(a1.AroonUp() > 50 & a1.AroonDown() < 50){
						a1.UPTREND = true;
					}
				}
			}

			// if AROONUP < 50, and AROONDOWN > 50,  then DOWNTREND
			if (a3.AroonUp() > 50 && a3.AroonDown() < 50) {
				if(a3.AroonUp() > 50 && a3.AroonDown() < 50){
					if(a1.AroonUp() > 50 & a1.AroonDown() < 50){
						a1.UPTREND = true;
					}
				}
			}
			
			
		}	
		
		result =  result.stream().limit(size_result).collect(Collectors.toList());
		return result;
	}

	/** END KB **/


	public List<RSI_PatternData> RSI_IndicatorHander(RSI responseShort, RSI responseLong, int size_result) { 
		
		List<IndicatorData> rsiShort = responseShort.getData();
		List<IndicatorData> rsiLong = responseLong.getData();
		
		List<RSI_PatternData> result = new ArrayList<RSI_PatternData>();
		
		// copy RSI data to result list
		for (int i = 0; i < size_result+2; i++) {	
			// initialize an instance: date, rsiShort, rsiLong
			RSI_PatternData tmp = new RSI_PatternData(rsiShort.get(i).getDateTime(), rsiShort.get(i).getData(), rsiLong.get(i).getData());
			result.add(tmp);
		}
		
		// analyse [size_result] days' RSI
		for (int i = 0; i < size_result; i++) {
			
			RSI_PatternData j0 = result.get(i); // day j
			RSI_PatternData j1 = result.get(i+1); // day j-1
			RSI_PatternData j2 = result.get(i+2); // day j-2
			
			// if RSI > 70, then overbought
			if (j0.getRsiLong() > 70) j0.setOverboughtLong(true);
			if (j0.getRsiShort() > 70) j0.setOverboughtShort(true);
			
			// if RSI < 30, then oversold
			if (j0.getRsiLong() < 30) j0.setOversoldLong(true);
			if (j0.getRsiShort() < 30) j0.setOversoldShort(true);
			
			// if RSI > 50 and j > j-1 and j-1 > j-2, then trendUp
			if (j0.getRsiLong() > 50
					& j0.getRsiLong() > j1.getRsiLong()
					& j1.getRsiLong() > j2.getRsiLong()) j0.setTrendUpLong(true);
			if (j0.getRsiShort() > 50
					& j0.getRsiShort() > j1.getRsiShort()
					& j1.getRsiShort() > j2.getRsiShort()) j0.setTrendUpShort(true);
			
			// if RSI < 50 and j < j-1 and j-1 < j-2, then trendDown
			if (j0.getRsiLong() < 50
					& j0.getRsiLong() < j1.getRsiLong()
					& j1.getRsiLong() < j2.getRsiLong()) j0.setTrendDownLong(true);
			if (j0.getRsiShort() < 50
					& j0.getRsiShort() < j1.getRsiShort()
					& j1.getRsiShort() < j2.getRsiShort()) j0.setTrendDownShort(true);
			
			// case: short RSI rises and crosses long RSI, a bullish trend
			// if Short j > Long j and Short j-1 < Long j-1 and Short j > Short j-1, then shortUpCrossLong
			if (j0.getRsiShort() > j0.getRsiLong()
					& j1.getRsiShort() < j1.getRsiLong()
					& j0.getRsiShort() > j1.getRsiShort()) j0.setShortUpCrossLong(true);
			
			// case: short RSI declines and crosses long RSI, a bearish trend
			// if Short j < Long j and Short j-1 > Long j-1 and Short j < Short j-1, then shortDownCrossLong
			if (j0.getRsiShort() < j0.getRsiLong()
					& j1.getRsiShort() > j1.getRsiLong()
					& j0.getRsiShort() < j1.getRsiShort()) j0.setShortDownCrossLong(true);
		}
		
		// only return [size_result] days' RSI analysis
		result =  result.stream().limit(size_result).collect(Collectors.toList());
		return result;
	}
	
	public List<STOCH_PatternData> STOCH_IndicatorHandler(STOCHF response_STOCH_fast, STOCH response_STOCH_slow, int size_result) {
		
		List<STOCHDataFast> stochFast = response_STOCH_fast.getData();
		List<STOCHDataSlow> stochSlow = response_STOCH_slow.getData();
		
		List<STOCH_PatternData> result = new ArrayList<STOCH_PatternData>();
		
		// copy STOCH data to result list
		for (int i = 0; i < size_result+1; i++) {
			// initialize an instance: date, fastk, fastd(also called slowk), slowd
			STOCH_PatternData tmp = new STOCH_PatternData(stochFast.get(i).getDateTime(), stochFast.get(i).getFastK(), 
					stochSlow.get(i).getSlowK(), stochSlow.get(i).getSlowD());
			result.add(tmp);
		}
		
		// analyze STOCH
		for (int i = 0; i < size_result; i++) {
			
			STOCH_PatternData j0 = result.get(i); // day j
			STOCH_PatternData j1 = result.get(i+1); // day j-1
			
			// if fastK > 80, then overbought
			if (j0.getFastK() > 80) j0.setOverbought(true);
			
			// if fastK < 20, then oversold
			if (j0.getFastK() < 20) j0.setOversold(true);
			
			// if fastK goes down and crosses fastD(slowK), then sharpTrendDown
			if (j0.getFastK() < j1.getFastK()
					& j0.getFastK() < j0.getSlowK()
					& j1.getFastK() > j1.getSlowK())
				j0.setFastKDownCrossfastD(true);
			
			// if slowK goes down and crosses slowD, then smoothTrendDown
			if (j0.getSlowK() < j1.getSlowK()
					& j0.getSlowK() < j0.getSlowD()
					& j1.getSlowK() > j1.getSlowD())
				j0.setSlowKDownCrossSlowD(true);
			
			// if fastK goes up and crosses fastD(slowK), then sharpTrendUp
			if (j0.getFastK() > j1.getFastK()
					& j0.getFastK() > j0.getSlowK()
					& j1.getFastK() < j1.getSlowK())
				j0.setFastKUpCrossfastD(true);
			
			// if slowK goes up and crosses slowD, then smoothTrendUp
			if (j0.getSlowK() > j1.getSlowK()
					& j0.getSlowK() > j0.getSlowD()
					& j1.getSlowK() < j1.getSlowD())
				j0.setSlowKUpCrossslowD(true);
		}
		
		// only return [size_result] days' RSI analysis
		result =  result.stream().limit(size_result).collect(Collectors.toList());
		return result;
	}
	
	public List<WILLR_PatternData> WILLR_IndicatorHandler(WILLR response_WILLR, int size_result){
	//public List<WILLR_PatternData> WILLR_IndicatorHandler(List<IndicatorData> willrData, int size_result){
		
		List<IndicatorData> willrData = response_WILLR.getData();
		
		//System.out.println(" ");
		
		List<WILLR_PatternData> result = new ArrayList<WILLR_PatternData>();
		
		for (int i = 0; i < size_result; i++) {
			WILLR_PatternData tmp = new WILLR_PatternData(willrData.get(i).getDateTime(), willrData.get(i).getData());
			if (-20 < willrData.get(i).getData()) tmp.setOverbought(true);
			if (willrData.get(i).getData() < -80) tmp.setOversold(true);
			result.add(tmp);
		}
		
		return result;
	}
	
}
