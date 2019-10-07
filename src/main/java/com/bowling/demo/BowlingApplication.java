package com.bowling.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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
		String filePath = "";
		if (params.length > 0 && params[0] != null) {
			filePath = params[0];
		} else {
			Resource resource = new ClassPathResource("data/data1.dat");
			filePath = resource.getFile().getPath();
		}
		logger.info("Application started with command line arguments: ", filePath);
		List<Player> players = playerService.parseDataFromFile(filePath);
		List<Player> data = playerService.calculateScore(players);
		printer.print(data);
	}
}