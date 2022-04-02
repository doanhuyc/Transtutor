package main.controller;

import main.gui.SearchMenu;
import main.model.Result;

public class SearchController extends Controller<SearchMenu> {
	public static final String INDEX = "SEARCH";

	@Override
	String doAction(SearchMenu menu, Result result) {
		switch (menu) {

			case BY_ID:
				break;
			case BY_NAME:
				break;
			case BACK:
				return MainMenuController.INDEX;
			case QUIT:
				return END;
		}
		return null;
	}

	@Override
	Class<SearchMenu> getClassMenu() {
		return SearchMenu.class;
	}

	@Override
	String getControllerIndex() {
		return INDEX;
	}
}
