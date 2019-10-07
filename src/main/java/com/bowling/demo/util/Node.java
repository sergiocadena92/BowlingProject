package com.bowling.demo.util;

public class Node<T> {

	T element;
	Node<T> previous;
	Node<T> next;

	public Node(T element) {
		this.element = element;
		this.previous = null;
		this.next = null;
	}

	public T getElement() {
		return element;
	}

	public Node<T> getPrevious() {
		return previous;
	}

	public Node<T> getNext() {
		return next;
	}
	
	public boolean hasNext() {
		return this.next != null;
	}
}
