package main.controller;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;

import main.gui.InputMenuFile;
import main.model.Result;
import main.service.FileReaderService;
import main.service.ImportService;

public class InputMenuController extends Controller<InputMenuFile> {
	public static final String INDEX = "MENU_INPUT";
	private final FileReaderService fileReaderService = new FileReaderService();
	private final ImportService importService = new ImportService();

	@Override
	String doAction(InputMenuFile menu, Result result) {
		switch (menu) {
			case INPUT_PATH:
				return doActionInputPath(result);
			case INPUT_STRING:
				return doActionInputString(result);
			case QUIT:
				return END;
		}
		return null;
	}

	@Override
	Class<InputMenuFile> getClassMenu() {
		return InputMenuFile.class;
	}

	@Override
	String getControllerIndex() {
		return INDEX;
	}

	private String doActionInputString(Result result) {
		log("String to input");
		String string = scanUserInput();

		List<String> strings = fileReaderService.readString(string);
		updateResult(result, strings);

		return MainMenuController.INDEX;
	}

	private String doActionInputPath(Result result) {
		log("File path to input");
		String path = scanUserInput();

		try {
			List<String> strings = fileReaderService.readFileFromPath(path);
			updateResult(result, strings);

			return MainMenuController.INDEX;
		} catch (InvalidPathException e) {
			log("Invalid path");
			return INDEX;
		} catch (IOException e) {
			log("File does'nt existed");
			return INDEX;
		}
	}

	private void updateResult(Result resultToUpdate, List<String> strings) {
		Result res = importService.importString(strings);

		resultToUpdate.setRecords(res.getRecords());
		resultToUpdate.setBadRecords(res.getBadRecords());
	}
}
