package com.bowling.demo.file;

import java.util.List;

import com.bowling.demo.exception.BowlingException;

public interface FileReader {

	List<String> readFile(String pathFile) throws BowlingException;
}
