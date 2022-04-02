package main.controller;

import main.gui.Menu;
import main.model.Result;

public class QuitController extends Controller {
	@Override
	Class<? extends Controller> doAction(Menu menu, Result result) {
		return null;
	}

	@Override
	Class getClassMenu() {
		return null;
	}
}
