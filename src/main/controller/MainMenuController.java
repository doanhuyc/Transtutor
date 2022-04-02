package main.controller;

import java.util.List;

import main.gui.MainMenu;
import main.model.Record;
import main.model.Result;

public class MainMenuController extends Controller<MainMenu> {
	public static final String INDEX = "MAIN_MENU";

	@Override
	String doAction(MainMenu menu, Result result) {
		switch (menu) {
			case PRINT_ALL_VALID:
				return doPrintAllValid(result.getRecords());
			case PRINT_ALL_INVALID:
				return doPrintAllInValid(result.getBadRecords());
			case SEARCH_BY_ID_NAME:
				return SearchController.INDEX;
			case PRINT_REPORT:
			case BACK:
				return InputMenuController.INDEX;
			case QUIT:
				return END;
		}
		return null;
	}

	@Override
	Class<MainMenu> getClassMenu() {
		return MainMenu.class;
	}

	@Override
	String getControllerIndex() {
		return INDEX;
	}

	private String doPrintAllValid(List<Record> recordList) {
		logListObject(recordList);
		return INDEX;
	}

	private String doPrintAllInValid(List<String> badRecord) {
		logListObject(badRecord);
		return INDEX;
	}
}
