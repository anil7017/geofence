package com.ltts;

public class Location {

	private double lt;
	private double lg;

	public Location(double lt, double lg) {
		super();
		this.lt = lt;
		this.lg = lg;
	}

	public Location() {
		super();
	}

	public double getLt() {
		return lt;
	}

	public void setLt(double lt) {
		this.lt = lt;
	}

	public double getLg() {
		return lg;
	}

	public void setLg(double lg) {
		this.lg = lg;
	}

}
