package com.example.indicators;

import java.time.LocalDateTime;

public class AROON_PatternData {
	
	//DATE
	private LocalDateTime datetime;

	//DECISION
	protected boolean DOWNTREND;
	protected boolean UPTREND;
	protected boolean SIDEWAY;
	
	//AROON VALUE
	protected double AroonUp;
	protected double AroonDown;
	
	public AROON_PatternData(LocalDateTime datetime, double AroonUp, double AroonDown) {
		this.datetime = datetime;
		this.AroonUp = AroonUp;
		this.AroonDown = AroonDown;
	}
	
}