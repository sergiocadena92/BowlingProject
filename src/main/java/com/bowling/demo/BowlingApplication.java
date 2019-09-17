package com.bowling.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bowling.demo.model.Player;
import com.bowling.demo.print.Printer;
import com.bowling.demo.service.PlayerService;

@SpringBootApplication
public class BowlingApplication implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(BowlingApplication.class);

	@Autowired
	private PlayerService playerService;
	@Autowired
	private Printer printer;

	public static void main(String[] args) {
		SpringApplication.run(BowlingApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String[] params = args.getSourceArgs();
		if (params[0] != null) {
			logger.info("File path:" + params[0]);
			List<Player> data = playerService.parseDataFromFile(params[0]);
			printer.print(data);
		}
		throw new Exception("File path must be passed as an argument from comand line");
	}
}
