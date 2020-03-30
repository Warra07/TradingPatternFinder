package com.example.indicators;

import java.time.LocalDateTime;

// RSI: a momentum oscillator to measure the speed of price movement
public class RSI_PatternData {
	
	private LocalDateTime datetime;
	private double rsiShort;
	private double rsiLong;
	
	private boolean overboughtShort;
	private boolean overboughtLong;
	private boolean oversoldShort;
	private boolean oversoldLong;
	
	private boolean trendUpShort;
	private boolean trendUpLong;
	private boolean trendDownShort;
	private boolean trendDownLong;
	
	private boolean shortUpCrossLong; // bullish sign
	private boolean shortDownCrossLong; // bearish sign
	
	public RSI_PatternData(LocalDateTime datetime, double s, double l) {
		this.datetime = datetime;
		this.rsiShort = s;
		this.rsiLong = l;
	}
	
	
	
	// getters and setters
	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public double getRsiShort() {
		return rsiShort;
	}

	public void setRsiShort(double rsiShort) {
		this.rsiShort = rsiShort;
	}

	public double getRsiLong() {
		return rsiLong;
	}

	public void setRsiLong(double rsiLong) {
		this.rsiLong = rsiLong;
	}

	public boolean isOverboughtShort() {
		return overboughtShort;
	}

	public void setOverboughtShort(boolean overboughtShort) {
		this.overboughtShort = overboughtShort;
	}

	public boolean isOversoldShort() {
		return oversoldShort;
	}

	public void setOversoldShort(boolean oversoldShort) {
		this.oversoldShort = oversoldShort;
	}

	public boolean isTrendUpShort() {
		return trendUpShort;
	}

	public void setTrendUpShort(boolean trendUpShort) {
		this.trendUpShort = trendUpShort;
	}

	public boolean isTrendDownShort() {
		return trendDownShort;
	}

	public void setTrendDownShort(boolean trendDownShort) {
		this.trendDownShort = trendDownShort;
	}

	public boolean isOverboughtLong() {
		return overboughtLong;
	}

	public void setOverboughtLong(boolean overboughtLong) {
		this.overboughtLong = overboughtLong;
	}

	public boolean isOversoldLong() {
		return oversoldLong;
	}

	public void setOversoldLong(boolean oversoldLong) {
		this.oversoldLong = oversoldLong;
	}

	public boolean isTrendUpLong() {
		return trendUpLong;
	}

	public void setTrendUpLong(boolean trendUpLong) {
		this.trendUpLong = trendUpLong;
	}

	public boolean isTrendDownLong() {
		return trendDownLong;
	}

	public void setTrendDownLong(boolean trendDownLong) {
		this.trendDownLong = trendDownLong;
	}

	public boolean isShortUpCrossLong() {
		return shortUpCrossLong;
	}

	public void setShortUpCrossLong(boolean shortUpCrossLong) {
		this.shortUpCrossLong = shortUpCrossLong;
	}

	public boolean isShortDownCrossLong() {
		return shortDownCrossLong;
	}

	public void setShortDownCrossLong(boolean shortDownCrossLong) {
		this.shortDownCrossLong = shortDownCrossLong;
	}
	
}