package com.bowling.demo.service;

import java.util.List;

import com.bowling.demo.model.Player;

public interface PlayerService {

	List<Player> parseDataFromFile(String pathFile);
}