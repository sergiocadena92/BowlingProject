package com.bowling.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bowling.demo.exception.BowlingException;
import com.bowling.demo.file.FileReader;
import com.bowling.demo.model.Frame;
import com.bowling.demo.model.Player;
import com.bowling.demo.print.Printer;
import com.bowling.demo.service.PlayerService;
import com.bowling.demo.util.DoublyLinkedList;
import com.bowling.demo.util.Node;
import com.bowling.demo.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BowlingUnitTests {
    
	@Autowired
	private FileReader fileReader;
	@Autowired
	private Printer printer;
	@Autowired
	private PlayerService playerService;
		
	@Test
	public void testDoublyLinkedList() {
		DoublyLinkedList<String> list = new DoublyLinkedList<>();
		list.add("Fist element");
		list.add("Second element");
		list.add("Third element");
		
		Node<String> element = list.getHead();
		assertEquals("Fist element", element.getElement());
		element = element.getNext();
		assertEquals("Second element", element.getElement());
		element = element.getNext();
		assertEquals("Third element", element.getElement());
	}
	
	@Test
	public void testDoublyLinkedListHead() {
		DoublyLinkedList<String> list = new DoublyLinkedList<>();
		list.add("Fist element");
		list.add("Second element");
		list.add("Third element");
		
		assertEquals("Fist element", list.getHead().getElement());
	}
	
	@Test
	public void testFileReaderContent() {
		Resource resource = new ClassPathResource("data/data2.dat");
		try {
			List<String> readFile = fileReader.readFile(resource.getFile().getPath());
			assertEquals("Jeff	10", readFile.get(0));
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test
	public void testFileReaderSize() {
		Resource resource = new ClassPathResource("data/data2.dat");
		try {
			List<String> readFile = fileReader.readFile(resource.getFile().getPath());
			assertEquals(24, readFile.size());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test
	public void testPlayerThrowsLessThan10() {
		Resource resource = new ClassPathResource("data/data7.dat");
		try {
			List<String> lines = fileReader.readFile(resource.getFile().getPath());
			Map<String, Integer> throwsByPlayer = Util.getTthrowsByPlayer(lines);
			int playerThrows = throwsByPlayer.entrySet().stream().findAny().get().getValue();
			assertEquals(1, playerThrows);
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test
	public void testPlayerThrowsEquals10() {
		Resource resource = new ClassPathResource("data/data1.dat");
		try {
			List<String> lines = fileReader.readFile(resource.getFile().getPath());
			Map<String, Integer> throwsByPlayer = Util.getTthrowsByPlayer(lines);
			int playerThrows = throwsByPlayer.entrySet().stream().findAny().get().getValue();
			assertEquals(10, playerThrows);
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test
	public void testPlayerThrowsGreaterThan10() {
		Resource resource = new ClassPathResource("data/data9.dat");
		try {
			List<String> lines = fileReader.readFile(resource.getFile().getPath());
			Map<String, Integer> throwsByPlayer = Util.getTthrowsByPlayer(lines);
			int playerThrows = throwsByPlayer.entrySet().stream().findAny().get().getValue();
			assertEquals(12, playerThrows);
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test(expected = BowlingException.class)
	public void testNonExistingFile() throws BowlingException {
		fileReader.readFile("/noFile.dat");
	}
	
	@Test
	public void testIsNumberTrue() {
		boolean isNumber = Util.isNumber("123456");
		assertEquals(true, isNumber);
	}
	
	@Test
	public void testIsNumberFalse() {
		boolean isNumber = Util.isNumber("142/45");
		assertEquals(false, isNumber);
	}
	
	@Test
	public void testCalculateScore() {
		boolean isNumber = Util.isNumber("142/45");
		assertEquals(false, isNumber);
	}

	@Test(expected = BowlingException.class)
	public void testPrintEmptyPlayerList() throws BowlingException {
		printer.print(new ArrayList<Player>());
	}
	
	@Test
	public void testParseDataSize() {
		Resource resource = new ClassPathResource("data/data1.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			assertEquals(10, players.get(0).getFrames().getSize());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test
	public void testPartialScoreFirstPlayer() {
		Resource resource = new ClassPathResource("data/data1.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			Player player = players.get(0);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(9, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(8, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(6, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(19, frame.getElement().getScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}
	
	@Test
	public void testPartialScoreSecondPlayer() {
		Resource resource = new ClassPathResource("data/data1.dat");
		try {
			File file = resource.getFile();
			List<Player> players = playerService.parseDataFromFile(file.getPath());
			Player player = players.get(1);
			Node<Frame> frame = player.getFrames().getHead();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(9, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(9, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(9, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(10, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(8, frame.getElement().getScore());
			frame = frame.getNext();
			assertEquals(19, frame.getElement().getScore());
		} catch (IOException | BowlingException e) {
			fail();
		}
	}

}
