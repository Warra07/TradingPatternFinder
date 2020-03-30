package com.example.indicators;

import java.time.LocalDateTime;

public class ATR_PatternData {
	
	//DATE
	private LocalDateTime datetime;

	//DECISION
	protected boolean STRENGTH_MOVE;
	
	//AROON VALUE
	protected double ATR_VALUE;
	protected double MEAN_ATR_VALUE;
	
	public AROON_PatternData(LocalDateTime datetime, double atr_value, double mean) {
		this.datetime = datetime;
		this.ATR_VALUE = atr_value;
		this.MEAN_ATR_VALUE = mean;
	}
	
}