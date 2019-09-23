package com.bowling.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowling.demo.exception.BowlingException;
import com.bowling.demo.file.FileReader;
import com.bowling.demo.model.Frame;
import com.bowling.demo.model.Player;
import com.bowling.demo.util.Node;
import com.bowling.demo.util.Util;

@Service
public class PlayerServiceImpl implements PlayerService {

	private static final String STRIKE_SCORE = "10";
	private static final String LINE_SEPARATOR = "\\t";
	private final FileReader fileReader;

	@Autowired
	public PlayerServiceImpl(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	@Override
	public List<Player> parseDataFromFile(String pathFile) throws BowlingException {
		List<String> lines = fileReader.readFile(pathFile);
		Map<String, Player> data = new HashMap<>();
		for (String line : lines) {
			String[] playerPinfall = line.split(LINE_SEPARATOR);
			String playerName = playerPinfall[0];
			String pinfall = playerPinfall[1];
			checkData(pinfall);
			if (data.containsKey(playerName)) {
				Player player = data.get(playerName);
				Node<Frame> tail = player.getFrames().getTail();
				if (tail.getElement().isStrike()) {
					if (tail.getElement().getChance() == 10) {
						tail.getElement().addPinfall(pinfall);
					} else {
						Frame frame = new Frame(tail.getElement().getChance() + 1);
						frame.addPinfall(pinfall);
						frame.setStrike(pinfall.equals(STRIKE_SCORE) ? true : false);
						player.addFrame(frame);
					}
				} else {
					if (tail.getElement().getPinfallSize() < 2) {
						tail.getElement().addPinfall(pinfall);
					} else {
						Frame frame = new Frame(tail.getElement().getChance() + 1);
						frame.addPinfall(pinfall);
						frame.setStrike(pinfall.equals(STRIKE_SCORE) ? true : false);
						player.addFrame(frame);
					}
				}
			} else {
				Frame frame = new Frame(1);
				if (pinfall.equals(STRIKE_SCORE)) {
					frame.setStrike();
				} else {
					frame.addPinfall(pinfall);
					frame.setStrike(false);
				}
				Player player = new Player(playerName);
				player.addFrame(frame);
				data.put(playerName, player);
			}
		}
		List<Player> playersScore = calculateScore(data.values().stream().collect(Collectors.toList()));
		return playersScore;
	}

	private List<Player> calculateScore(List<Player> players) {
		for (Player player : players) {
			Node<Frame> currentFrame = player.getFrames().getHead();
			while (currentFrame != null) {
				if (currentFrame.getElement().isStrike()) {
					calculateStrikeScore(currentFrame);
				} else {
					int currentScore = currentFrame.getElement().getScore();
					int previousScore = 0;
					if (currentFrame.getPrevious() != null) {
						previousScore = currentFrame.getPrevious().getElement().getTotalScore();
					}
					if (currentFrame.getElement().getScore() == 10 && currentFrame.getNext() != null) {
						int nextScore = currentFrame.getNext().getElement().getFirstPinfallScore();
						currentFrame.getElement().setTotalScore(previousScore + currentScore + nextScore);
					} else {
						currentFrame.getElement().setTotalScore(previousScore + currentScore);
					}
				}
				currentFrame = currentFrame.getNext();
			}
		}
		return players;
	}

	private void calculateStrikeScore(Node<Frame> currentFrame) {
		int previousScore = 0;
		if (currentFrame.getPrevious() != null) {
			previousScore = currentFrame.getPrevious().getElement().getTotalScore();
		}
		int currentScore = currentFrame.getElement().getScore();
		if (currentFrame.getNext() != null) {
			if (currentFrame.getNext().getNext() == null) {
				int nextScore = currentFrame.getNext().getElement().getFistTwoPinfalls();
				currentFrame.getElement().setTotalScore(previousScore + currentScore + nextScore);
			} else {
				int nextScore = currentFrame.getNext().getElement().getScore();
				currentFrame.getElement().setTotalScore(previousScore + currentScore + nextScore);
				if (currentFrame.getNext().getElement().isStrike()) {
					currentScore = currentFrame.getElement().getTotalScore();
					nextScore = currentFrame.getNext().getNext().getElement().getFirstPinfallScore();
					currentFrame.getElement().setTotalScore(currentScore + nextScore);
				}
			}
		} else {
			currentFrame.getElement().setTotalScore(previousScore + currentScore);
		}
	}

	private void checkData(String pinfall) throws BowlingException {
		if (Util.isNumber(pinfall)) {
			int number = Integer.parseInt(pinfall);
			if (number < 0 || number > 10) {
				throw new BowlingException("Pinfall cannot be a number less than 0 or greater than 10");
			}
		} else if (!pinfall.equalsIgnoreCase("F")) {
			throw new BowlingException("Foul must be represented by an F character");
		}
	}
}
