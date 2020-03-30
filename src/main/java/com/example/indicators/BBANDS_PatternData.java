package com.example.indicators;

import java.time.LocalDateTime;

public class BBANDS_PatternData {
	
	//DATE
	private LocalDateTime datetime;

	//DECISION
	protected boolean OVERBOUGHT;
	protected boolean OVERSOLD;
	protected boolean SIDEWAY;
	
	//BBANDS VALUE
	protected double UPPERBANDS;
	protected double MIDDLEBANDS;
	protected double LOWERBANDS;
	protected double CLOSEPRICE;
	
	public BBANDS_PatternData(LocalDateTime datetime, double closePrice, double upperBands, double middleBand, double lowerBand) {
		this.datetime = datetime;
		this.CLOSEPRICE = closePrice;
		this.UPPERBANDS = upperBands;
		this.MIDDLEBANDS = middleBand;
		this.LOWERBANDS = lowerBand;
	}
	
}