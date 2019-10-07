package com.bowling.demo.util;

import static com.bowling.demo.enumeration.Constant.LINE_SEPARATOR;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bowling.demo.exception.BowlingException;

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
	
	public static Map<String, Integer> getTthrowsByPlayer(List<String> lines) throws BowlingException {
		if (lines.isEmpty()) {
			throw new BowlingException("Player file cannot be empty");
		}
		DoublyLinkedList<String> players = listToDoublyLinkedList(lines);
		Map<String, Integer> throwsByPlayer = new HashMap<>();
		Node<String> element = players.getHead();
		while (element != null && element.hasNext()) {
			element = iterateToTail(element);
			if (throwsByPlayer.containsKey(element.getElement())) {
				throwsByPlayer.merge(element.getElement(), 1, Integer::sum);
			} else {
				throwsByPlayer.put(element.getElement(), 1);
			}
			element = element.getNext();
		}
		return throwsByPlayer;
	}

	private static Node<String> iterateToTail(Node<String> element) {
		if (element.hasNext() && element.getNext().getElement().equals(element.getElement())) {
			return iterateToTail(element.getNext());
		}
		return element;
	}

	private static DoublyLinkedList<String> listToDoublyLinkedList(List<String> lines) {
		DoublyLinkedList<String> players = new DoublyLinkedList<>();
		for (String line : lines) {
			String[] playerPinfall = line.split(LINE_SEPARATOR.getValue());
			String playerName = playerPinfall[0];
			players.add(playerName);
		}
		return players;
	}
}
