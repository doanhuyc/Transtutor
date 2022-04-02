package main.controller;

import java.util.List;

import main.gui.MainMenu;
import main.model.Record;
import main.model.Result;

public class MainMenuController extends Controller<MainMenu> {
	@Override
	Class<? extends Controller> doAction(MainMenu menu, Result result) {
		switch (menu) {
			case PRINT_ALL_VALID:
				return doPrintAllValid(result.getRecords());
			case PRINT_ALL_INVALID:
				return doPrintAllInValid(result.getBadRecords());
			case SEARCH_BY_ID_NAME:
				return SearchController.class;
			case PRINT_REPORT:
			case BACK:
				return InputMenuController.class;
			case QUIT:
			default:
				return QuitController.class;
		}
	}

	@Override
	Class<MainMenu> getClassMenu() {
		return MainMenu.class;
	}

	private Class<? extends Controller> doPrintAllValid(List<Record> recordList) {
		logListObject(recordList);
		return getClass();
	}

	private Class<? extends Controller> doPrintAllInValid(List<String> badRecord) {
		logListObject(badRecord);
		return getClass();
	}
}
