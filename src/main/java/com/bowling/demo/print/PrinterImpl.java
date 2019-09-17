package com.bowling.demo.print;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bowling.demo.model.Frame;
import com.bowling.demo.model.Player;

@Component
public class PrinterImpl implements Printer {

	@Override
	public void print(List<Player> players) {
		for (Player player : players) {
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
		String[][] data = createEmptyMatrix(3, 11);
		data[0][0] = String.format("%-10s", player.getName());
		data[1][0] = String.format("%-10s", "Pinfall");
		data[2][0] = String.format("%-10s", "Score");
		for (int i = 0; i < player.getFrames().size(); i++) {
			Frame frame = player.getFrames().get(i);
			if (frame.isLastFrame()) {
				if (frame.getFirstPinFall().equals("10")) {
					data[1][i + 1] = String.format("%-5s",
							"X" + "  " + frame.getSecondPinFall() + "  " + frame.getThirdPinFall());
				} else {
					data[1][i + 1] = String.format("%-5s", frame.getFirstPinFall() + "  " + frame.getSecondPinFall());
				}
			} else {
				if (frame.isStrike()) {
					data[1][i + 1] = String.format("%-5s", "X");
				} else {
					data[1][i + 1] = String.format("%-5s", frame.getFirstPinFall() + "  " + frame.getSecondPinFall());
				}
			}
		}
		return data;
	}

	private String[][] createEmptyMatrix(int rows, int cols) {
		String[][] data = new String[rows][cols];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = "";
			}
		}
		return data;
	}
}
