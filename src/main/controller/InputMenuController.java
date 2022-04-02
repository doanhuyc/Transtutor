package main.controller;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;

import main.gui.InputMenuFile;
import main.model.Record;
import main.service.FileReaderService;
import main.service.InventoryService;

public class InputMenuController extends Controller<InputMenuFile> {
	private final FileReaderService fileReaderService;
	private final InventoryService inventoryService;

	public InputMenuController(FileReaderService fileReaderService, InventoryService inventoryService) {
		this.fileReaderService = fileReaderService;
		this.inventoryService = inventoryService;
	}

	@Override
	Class<? extends Controller> doAction(InputMenuFile menu) {
		switch (menu) {
			case INPUT_PATH:
				return doActionInputPath();
			case INPUT_STRING:
				return doActionInputString();
			case QUIT:
			default:
				return null;
		}
	}

	@Override
	Class<InputMenuFile> getClassMenu() {
		return InputMenuFile.class;
	}

	private Class<? extends Controller> doActionInputString() {
		log("String to input");
		String string = scanUserInput();

		List<Record> records = fileReaderService.readString(string);
		return importRecords(records);
	}

	private Class<? extends Controller> doActionInputPath() {
		log("File path to input");
		String path = scanUserInput();

		try {
			List<Record> records = fileReaderService.readFileFromPath(path);
			return importRecords(records);
		} catch (InvalidPathException e) {
			log("Invalid path");
			return getClass();
		} catch (IOException e) {
			log("File doesn't existed");
			return getClass();
		}
	}

	private Class<? extends Controller> importRecords(List<Record> records) {
		if (records.isEmpty()) {
			log("File is empty");
			return getClass();
		}

		try {
			inventoryService.saveAll(records);
			log(inventoryService.findAllRecords().size() + " records saved");
			log(inventoryService.findAllBadRecords().size() + " bad records saved");
			return MainMenuController.class;
		} catch (ArrayStoreException e) {
			log("File is too big, array boundaries exceed");
			return getClass();
		}
	}
}
