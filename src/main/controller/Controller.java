package main.controller;

import java.util.List;
import java.util.Scanner;

import main.gui.Menu;
import main.model.Result;

public abstract class Controller<M extends Menu> {
	private static final String EMPTY = "******Empty*******";

	public Class<? extends Controller> process(Result result) {
		for (M menu : Menu.values(getClassMenu())) {
			log(menu.getDescriptionToPrint());
		}

		String choice = scanUserInput();

		M menu = Menu.get(getClassMenu(), choice);
		if (menu == null) {
			log("Choice is invalid");
			return getClass();
		}
		return doAction(menu, result);
	}

	abstract Class<? extends Controller> doAction(M menu, Result result);

	abstract Class<M> getClassMenu();

	void log(String message) {
		System.out.println(message);
	}

	String scanUserInput() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	<E> void logListObject(List<E> objectList) {
		if (objectList.isEmpty()) {
			log(EMPTY);
		} else {
			for (Object object : objectList) {
				log(object.toString());
			}
			log("There are " + objectList.size() + " item(s)");
		}
	}
}
