package com.example.indicators;

import java.time.LocalDateTime;

public class CCI_PatternData {
	
	//DATE
	private LocalDateTime datetime;

	//DECISION
	protected boolean OVERBOUGHT;
	protected boolean OVERBSELL;

	protected boolean BEARISH_CCI_DIVERGENCE;
	protected boolean BULLISH_CCI_DIVERGENCE;
	
	//AROON VALUE
	protected double CCI_VALUE;
	protected double AroonDown;
	
	public CCI_PatternData(LocalDateTime datetime, double cci_value) {
		this.datetime = datetime;
		this.CCI_VALUE = cci_value;
	}
	
}