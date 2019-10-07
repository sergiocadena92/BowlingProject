package com.bowling.demo.enumeration;

public enum Constant {

	STRIKE_SCORE("10"), 
	LINE_SEPARATOR("\\t");

	private String value;

	private Constant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
