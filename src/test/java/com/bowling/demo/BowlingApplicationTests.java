package com.bowling.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bowling.demo.model.Player;
import com.bowling.demo.service.PlayerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BowlingApplicationTests {
	@Autowired
	private PlayerService playerService;

	@Test
	public void testCalculateScore() {
		Resource resource = new ClassPathResource("data/data1.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			assertEquals(20, players.get(0).getFrames().get(0).getScore());
		} catch (IOException e) {
			fail();
		}
	}

}
