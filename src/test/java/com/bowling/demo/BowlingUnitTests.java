package com.bowling.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bowling.demo.file.FileReader;
import com.bowling.demo.util.DoublyLinkedList;
import com.bowling.demo.util.Node;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BowlingUnitTests {
    
	@Autowired
	private FileReader fileReader;
	
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
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void testFileReaderSize() {
		Resource resource = new ClassPathResource("data/data2.dat");
		try {
			List<String> readFile = fileReader.readFile(resource.getFile().getPath());
			assertEquals(24, readFile.size());
		} catch (IOException e) {
			fail();
		}
	}

}
