package com.bowling.demo.print;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bowling.demo.model.Frame;
import com.bowling.demo.model.Player;
import com.bowling.demo.util.Node;
import com.bowling.demo.util.Util;

@Component
public class PrinterImpl implements Printer {

	@Override
	public void print(List<Player> players) {
		System.out.println();
		print(fillFrame());
		System.out.println();
		for (Player player : players) {
			System.out.println(player.getName());
			String[][] data = formatData(player);
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					System.out.print(data[i][j]);
				}
				System.out.println();
			}
		}
	}

	private String[][] formatData(Player player) {
		String[][] data = new String[2][11];
		data[0][0] = String.format("%-10s", "Pinfall");
		data[1][0] = String.format("%-10s", "Score");
		Node<Frame> nodeFrame = player.getFrames().getHead();
		fillPinfalls(data, nodeFrame);
		fillScore(data, nodeFrame);
		return data;
	}

	private void fillPinfalls(String[][] data, Node<Frame> nodeFrame) {
		for (int i = 1; i < data[0].length; i++) {
			if (nodeFrame != null) {
				StringBuilder value = new StringBuilder();
				int result = 0;
				for (String pinfall : nodeFrame.getElement().getPinfalls()) {
					if (nodeFrame.getElement().isStrike()) {
						value.append("X");
					} else {
						if (Util.isNumber(pinfall)) {
							result += Integer.parseInt(pinfall);
						}
						if (result == 10) {
							value.append("/");
						} else {
							value.append(pinfall);
						}
					}
				}
				String formatedValue = padding(value.toString());
				data[0][i] = formatedValue;
				nodeFrame = nodeFrame.getNext();
			}
		}
	}

	private String padding(String value) {
		StringBuilder formatedValue = new StringBuilder();
		for (char character : value.toCharArray()) {
			formatedValue.append(String.format("%-" + (6 / value.length()) + "s", character));
		}
		return formatedValue.toString();
	}

	private void fillScore(String[][] data, Node<Frame> nodeFrame) {
		for (int i = 1; i < data[1].length; i++) {
			if (nodeFrame != null) {
				data[1][i] = String.format("%-6s", nodeFrame.getElement().getTotalScore());
				nodeFrame = nodeFrame.getNext();
			}
		}
	}

	private String[] fillFrame() {
		String[] frame = new String[11];
		frame[0] = String.format("%-10s", "Frame");
		for (int i = 1; i < frame.length; i++) {
			frame[i] = String.format("%-6s", i);
		}
		return frame;
	}

	private void print(String[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]);
		}
	}
}
