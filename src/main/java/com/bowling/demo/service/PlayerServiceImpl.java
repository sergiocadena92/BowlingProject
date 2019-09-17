package com.bowling.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowling.demo.file.FileReader;
import com.bowling.demo.model.Frame;
import com.bowling.demo.model.Player;

@Service
public class PlayerServiceImpl implements PlayerService {

	private static final String STRIKE_SCORE = "10";
	private static final String LINE_SEPARATOR = " ";
	private final FileReader fileReader;

	@Autowired
	public PlayerServiceImpl(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	@Override
	public List<Player> parseDataFromFile(String pathFile) {
		List<String> lines = fileReader.readFile(pathFile);
		Map<String, Player> data = new HashMap<>();
		for (String line : lines) {
			String[] playerScore = line.split(LINE_SEPARATOR);
			String playerName = playerScore[0];
			String score = playerScore[1];
			if (data.containsKey(playerName)) {
				Player player = data.get(playerName);
				Frame frame = player.getFrames().getLast();
				if (frame.isLastFrame()) {
					if (frame.getFirstPinFall().equals(STRIKE_SCORE)) {
						if (frame.isSecondPinFall()) {
							frame.setThirdPinFall(score);
						} else {
							frame.setSecondPinFall(score);
						}
					} else {
						frame.setSecondPinFall(score);
					}
				} else {
					if (frame.isStrike()) {
						Frame newFrame = new Frame(frame.getChance() + 1);
						if (score.equals(STRIKE_SCORE)) {
							newFrame.setStrike();
						} else {
							newFrame.setFirstPinFall(score);
						}
						player.addFrame(newFrame);
					} else {
						if (frame.isSecondPinFall()) {
							Frame newFrame = new Frame(frame.getChance() + 1);
							if (score.equals(STRIKE_SCORE)) {
								newFrame.setStrike();
							} else {
								newFrame.setFirstPinFall(score);
							}
							player.addFrame(newFrame);
						} else {
							frame.setSecondPinFall(score);
						}
					}
				}
			} else {
				Frame frame = new Frame(1);
				frame.setLastFrame(false);
				if (score.equals(STRIKE_SCORE)) {
					frame.setStrike();
				} else {
					frame.setFirstPinFall(score);
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
		int previousScore = 0;
		for (Player player : players) {
			for (int i = 0; i < player.getFrames().size(); i++) {
				Frame frame = player.getFrames().get(i);
				if (frame.isStrike()) {
					frame.setScore(10 + previousScore);
					if (player.getFrames().get(i + 1) != null) {
						Frame nextFrame = player.getFrames().get(i + 1);
						if (nextFrame.isStrike() && player.getFrames().get(i + 2) != null) {
							Frame lastFrame = player.getFrames().get(i + 2);
							if (lastFrame.isStrike()) {
								frame.setScore(frame.getScore() + 10);
							} else {
								frame.setScore(frame.getScore() + Integer.parseInt(lastFrame.getFirstPinFall()));
							}
						} else if (isNumeric(nextFrame.getFirstPinFall())) {
							frame.setScore(frame.getScore() + Integer.parseInt(nextFrame.getFirstPinFall()));
						}
						if (isNumeric(nextFrame.getSecondPinFall())) {
							frame.setScore(frame.getScore() + Integer.parseInt(nextFrame.getSecondPinFall()));
						}
					}
					previousScore = frame.getScore();
				} else {
					frame.setScore(previousScore);
					if (isNumeric(frame.getFirstPinFall()) && isNumeric(frame.getSecondPinFall())) {
						int score = Integer.parseInt(frame.getFirstPinFall())
								+ Integer.parseInt(frame.getSecondPinFall());
						if (score == 10 && player.getFrames().get(i + 1) != null) {
							Frame nextFrame = player.getFrames().get(i + 1);
							if (isNumeric(nextFrame.getFirstPinFall())) {
								frame.setScore(
										frame.getScore() + score + Integer.parseInt(nextFrame.getFirstPinFall()));
							} else if (isNumeric(nextFrame.getSecondPinFall())) {
								frame.setScore(
										frame.getScore() + score + Integer.parseInt(nextFrame.getSecondPinFall()));
							}
						} else {
							frame.setScore(previousScore + score);
						}
						previousScore = frame.getScore();
					}

				}
			}
		}
		return players;
	}

	private boolean isNumeric(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
