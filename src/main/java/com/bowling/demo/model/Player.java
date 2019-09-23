package com.bowling.demo.model;

import com.bowling.demo.util.DoublyLinkedList;

public class Player {

	private String name;
	private DoublyLinkedList<Frame> frames;

	public Player(String name) {
		this.name = name;
		this.frames = new DoublyLinkedList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DoublyLinkedList<Frame> getFrames() {
		return frames;
	}

	public void addFrame(Frame frame) {
		this.frames.add(frame);
	}
}
