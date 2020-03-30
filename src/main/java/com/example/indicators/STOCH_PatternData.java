package com.example.indicators;

import java.time.LocalDateTime;

//Stochastic: closing prices near the same direction as the current trend
public class STOCH_PatternData {
	
	private LocalDateTime datetime;
	private double fastK;
	private double slowK; // slowK is also called fastD
	private double slowD;
	
	private boolean overbought;
	private boolean oversold;
	
	private boolean fastKDownCrossfastD;
	private boolean slowKDownCrossSlowD;
	
	private boolean fastKUpCrossfastD;
	private boolean slowKUpCrossslowD;
	
	public STOCH_PatternData(LocalDateTime datetime, double fastk, double slowk, double slowd) {
		this.datetime = datetime;
		this.fastK = fastk;
		this.slowK = slowk;
		this.slowD = slowd;
	}
	
	
	// getters and setters
	public LocalDateTime getDatetime() {
		return datetime;
	}


	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}


	public double getFastK() {
		return fastK;
	}


	public void setFastK(double fastK) {
		this.fastK = fastK;
	}


	public double getSlowK() {
		return slowK;
	}


	public void setSlowK(double slowK) {
		this.slowK = slowK;
	}


	public double getSlowD() {
		return slowD;
	}


	public void setSlowD(double slowD) {
		this.slowD = slowD;
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


	public boolean isFastKDownCrossfastD() {
		return fastKDownCrossfastD;
	}


	public void setFastKDownCrossfastD(boolean fastKDownCrossfastD) {
		this.fastKDownCrossfastD = fastKDownCrossfastD;
	}


	public boolean isSlowKDownCrossSlowD() {
		return slowKDownCrossSlowD;
	}


	public void setSlowKDownCrossSlowD(boolean slowKDownCrossSlowD) {
		this.slowKDownCrossSlowD = slowKDownCrossSlowD;
	}


	public boolean isFastKUpCrossfastD() {
		return fastKUpCrossfastD;
	}


	public void setFastKUpCrossfastD(boolean fastKUpCrossfastD) {
		this.fastKUpCrossfastD = fastKUpCrossfastD;
	}


	public boolean isSlowKUpCrossslowD() {
		return slowKUpCrossslowD;
	}


	public void setSlowKUpCrossslowD(boolean slowKUpCrossslowD) {
		this.slowKUpCrossslowD = slowKUpCrossslowD;
	}
	
	
	
}
