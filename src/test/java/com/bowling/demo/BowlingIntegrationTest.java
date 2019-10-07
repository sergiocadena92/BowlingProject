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

import com.bowling.demo.exception.BowlingException;
import com.bowling.demo.model.Frame;
import com.bowling.demo.model.Player;
import com.bowling.demo.service.PlayerService;
import com.bowling.demo.util.Node;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BowlingIntegrationTest {
	@Autowired
	private PlayerService playerService;
	
	@Test
	public void testFirstPlayerScore() {
		Resource resource = new ClassPathResource("data/data1.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			List<Player> data = playerService.calculateScore(players);
			Player player = data.get(0);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(20, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(39, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(48, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(66, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(74, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(84, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(90, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(120, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(148, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(167, frame.getElement().getTotalScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}

	@Test
	public void testSecondPlayerScore() {
		Resource resource = new ClassPathResource("data/data1.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			List<Player> data = playerService.calculateScore(players);
			Player player = data.get(1);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(16, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(25, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(44, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(53, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(82, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(101, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(110, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(124, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(132, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(151, frame.getElement().getTotalScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}

	@Test
	public void testPlayerPerfectScore() {
		Resource resource = new ClassPathResource("data/data2.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			List<Player> data = playerService.calculateScore(players);
			Player player = data.get(0);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(30, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(60, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(90, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(120, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(150, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(180, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(210, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(240, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(270, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(300, frame.getElement().getTotalScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}

	@Test
	public void testPlayerZeroScore() {
		Resource resource = new ClassPathResource("data/data3.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			List<Player> data = playerService.calculateScore(players);
			Player player = data.get(0);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}

	@Test
	public void testPlayerFoulScore() {
		Resource resource = new ClassPathResource("data/data4.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			List<Player> data = playerService.calculateScore(players);
			Player player = data.get(0);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(0, frame.getElement().getTotalScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test(expected = BowlingException.class)
	public void testWrongScore() throws BowlingException {
		Resource resource = new ClassPathResource("data/data5.dat");
		try {
			File file = resource.getFile();
			playerService.parseDataFromFile(file.getPath());
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test(expected = BowlingException.class)
	public void testWrongFoul() throws BowlingException {
		Resource resource = new ClassPathResource("data/data6.dat");
		try {
			File file = resource.getFile();
			playerService.parseDataFromFile(file.getPath());
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void test1Frame() {
		Resource resource = new ClassPathResource("data/data7.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			List<Player> data = playerService.calculateScore(players);
			Player player = data.get(0);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(30, frame.getElement().getTotalScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test
	public void test11Frames() {
		Resource resource = new ClassPathResource("data/data8.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			List<Player> data = playerService.calculateScore(players);
			Player player = data.get(1);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(6, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(24, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(32, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(40, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(70, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(100, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(124, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(143, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(152, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(160, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(165, frame.getElement().getTotalScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test
	public void test12Frames() {
		Resource resource = new ClassPathResource("data/data9.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			List<Player> data = playerService.calculateScore(players);
			Player player = data.get(0);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(9, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(20, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(22, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(29, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(49, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(68, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(77, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(77, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(83, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(92, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(100, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(118, frame.getElement().getTotalScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test
	public void test20Frames() {
		Resource resource = new ClassPathResource("data/data10.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			List<Player> data = playerService.calculateScore(players);
			Player player = data.get(0);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(6, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(26, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(38, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(40, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(49, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(61, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(70, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(84, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(91, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(100, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(108, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(112, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(139, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(159, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(179, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(194, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(199, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(212, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(221, frame.getElement().getTotalScore());
			frame = frame.getNext();
			assertEquals(230, frame.getElement().getTotalScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
}
