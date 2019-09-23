package com.bowling.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.bowling.demo.util.Util;

public class Frame {
	private int chance;
	private boolean strike;
	private List<String> pinfalls;
	private int score;
	private int totalScore;

	public Frame(int chance) {
		this.chance = chance;
		pinfalls = new ArrayList<>(3);
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public boolean isStrike() {
		return strike;
	}

	public void setStrike(boolean strike) {
		this.strike = strike;
	}

	public void setStrike() {
		this.pinfalls.add("10");
		this.strike = true;
		this.score = 10;

	}

	public void addPinfall(String pinfall) {
		this.pinfalls.add(pinfall);
		this.score += Util.isNumber(pinfall) ? Integer.parseInt(pinfall) : 0;
	}

	public int getFirstPinfallScore() {
		return Util.isNumber(pinfalls.get(0)) ? Integer.parseInt(pinfalls.get(0)) : 0;
	}

	public int getFistTwoPinfalls() {
		int counter = 0;
		int result = 0;
		for (String pinfall : pinfalls) {
			if (counter < 2 && Util.isNumber(pinfall)) {
				result += Integer.parseInt(pinfall);
				counter++;
			}
		}
		return result;
	}

	public List<String> getPinfalls() {
		return new ArrayList<>(pinfalls);
	}

	public int getPinfallSize() {
		return this.pinfalls.size();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
}
