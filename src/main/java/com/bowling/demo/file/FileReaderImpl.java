package com.bowling.demo.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
	
@Component
public class FileReaderImpl implements FileReader{
	
	@Override
	public List<String> readFile(String pathFile) {
		List<String> lines = null;
		try {
			lines = Files.lines(Paths.get(pathFile)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
