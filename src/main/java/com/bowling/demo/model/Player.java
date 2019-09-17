package com.bowling.demo.model;

import java.util.LinkedList;

public class Player {

	private String name;
	private LinkedList<Frame> frames;
	
	public Player(String name) {
		this.name = name;
		this.frames = new LinkedList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Frame> getFrames() {
		return new LinkedList<>(frames);
	}

	public void addFrame(Frame frame) {
		this.frames.add(frame);
	}
}
