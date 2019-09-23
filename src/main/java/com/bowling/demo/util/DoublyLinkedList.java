package com.bowling.demo.util;

public class DoublyLinkedList<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		size = 0;
	}

	public void add(T element) {
		Node<T> newElement = new Node<T>(element);
		if (isEmpty()) {
			head = newElement;
			tail = newElement;
		} else {
			tail.next = newElement;
			newElement.previous = tail;
			tail = newElement;
		}
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Node<T> getHead() {
		return head;
	}

	public Node<T> getTail() {
		return tail;
	}
}
