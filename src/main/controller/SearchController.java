package main.controller;

import main.gui.SearchMenu;
import main.model.Result;

public class SearchController extends Controller<SearchMenu> {
	@Override
	Class<? extends Controller> doAction(SearchMenu menu, Result result) {
		switch (menu) {

			case BY_ID:
				break;
			case BY_NAME:
				break;
			case BACK:
				return MainMenuController.class;
			case QUIT:
			default:
				return null;
		}
		return null;
	}

	@Override
	Class<SearchMenu> getClassMenu() {
		return SearchMenu.class;
	}
}
