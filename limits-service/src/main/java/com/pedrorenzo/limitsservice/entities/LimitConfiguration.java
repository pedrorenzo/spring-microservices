package com.pedrorenzo.limitsservice.entities;

public class LimitConfiguration {

	private int maximum;
	private int minimum;
	
	public LimitConfiguration(final int maximum, final int minimum) {
		this.maximum = maximum;
		this.minimum = minimum;
	}
	
	public LimitConfiguration() {
	}

	public int getMaximum() {
		return maximum;
	}

	public int getMinimum() {
		return minimum;
	}

}
