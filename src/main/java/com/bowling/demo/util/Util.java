package com.bowling.demo.util;

public class Util {
	
	private Util() {
		
	}

	public static boolean isNumber(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
