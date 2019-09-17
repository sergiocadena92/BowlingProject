package com.bowling.demo.model;

public class Frame {
	private int chance;
	private boolean strike;
	private boolean lastFrame;
	private String[] pinfalls;
	private int score;

	public Frame(int chance) {
		this.chance = chance;
		pinfalls = new String[3];
		if (chance == 10) {
			this.lastFrame = true;
		} else {
			this.lastFrame = false;
		}
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

	public boolean isLastFrame() {
		return lastFrame;
	}

	public void setLastFrame(boolean lastFrame) {
		this.lastFrame = lastFrame;
	}

	public void setFirstPinFall(String score) {
		this.pinfalls[0] = score;
	}

	public String getFirstPinFall() {
		return this.pinfalls[0];
	}

	public void setSecondPinFall(String score) {
		this.pinfalls[1] = score;
	}

	public String getSecondPinFall() {
		return this.pinfalls[1];
	}

	public String getThirdPinFall() {
		return this.pinfalls[2];
	}

	public boolean isSecondPinFall() {
		return this.pinfalls[1] != null;
	}

	public void setThirdPinFall(String score) {
		this.pinfalls[2] = score;
	}

	public void setStrike() {
		this.pinfalls[0] = "";
		this.pinfalls[1] = "10";
		this.strike = true;

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
