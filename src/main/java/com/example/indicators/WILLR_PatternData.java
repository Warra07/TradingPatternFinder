package com.example.indicators;

import java.time.LocalDateTime;

public class WILLR_PatternData {
	
	private LocalDateTime datetime;
	private double willr;
	
	private boolean overbought;
	private boolean oversold;
	
	public WILLR_PatternData(LocalDateTime dt, double w) {
		this.datetime = dt;
		this.willr = w;
	}
	
	// getters and setters
	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public double getWillr() {
		return willr;
	}

	public void setWillr(double willr) {
		this.willr = willr;
	}

	public boolean isOverbought() {
		return overbought;
	}

	public void setOverbought(boolean overbought) {
		this.overbought = overbought;
	}

	public boolean isOversold() {
		return oversold;
	}

	public void setOversold(boolean oversold) {
		this.oversold = oversold;
	}
	
}
