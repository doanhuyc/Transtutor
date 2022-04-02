package main.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileReaderService {
	private static final String SEPARATOR = "\t";

	/**
	 *
	 * @param str
	 * @return List of lines separated by Tab in the file
	 * @throws InvalidPathException if the path is invalid
	 * @throws IOException
	 *
	 */
	public List<String> readFileFromPath(String str) throws IOException {
		Path path = Paths.get(str);

		String file = Files.readString(path);
		return readString(file);
	}

	public List<String> readString(String str) {
		String[] lines = str.split(SEPARATOR);
		return Arrays.asList(lines);
	}
}
