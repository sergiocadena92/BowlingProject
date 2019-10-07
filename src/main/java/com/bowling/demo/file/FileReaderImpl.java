package com.bowling.demo.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bowling.demo.exception.BowlingException;

@Component
public class FileReaderImpl implements FileReader {

	private static final Logger logger = LoggerFactory.getLogger(FileReaderImpl.class);

	@Override
	public List<String> readFile(String pathFile) throws BowlingException {
		List<String> lines = null;
		try (Stream<String> stream = Files.lines(Paths.get(pathFile))) {
			lines = stream.collect(Collectors.toList());
		} catch (IOException e) {
			final String message = "An error occurred reading the file" + e.getCause(); 
			logger.error(message);
			throw new BowlingException(message);
		}
		return lines;
	}
}
