package com.bowling.demo.print;

import java.util.List;

import com.bowling.demo.exception.BowlingException;
import com.bowling.demo.model.Player;

public interface Printer {

	void print(List<Player> players) throws BowlingException;
}
