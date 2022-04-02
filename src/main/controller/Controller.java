package main.controller;

import java.util.List;
import java.util.Scanner;

import main.gui.Menu;
import main.model.Result;

public abstract class Controller<M extends Menu> {
	public final static String END = "END_PROGRAM";

	private static final String EMPTY = "******Empty*******";

	public String process(Result result) {
		for (M menu : Menu.values(getClassMenu())) {
			log(menu.getOrdinal() + ": " + menu.getDescription());
		}

		String choice = scanUserInput();

		M menu = Menu.get(getClassMenu(), choice);
		if (menu == null) {
			log("Choice is invalid");
			return getControllerIndex();
		}
		String index = doAction(menu, result);
		if (index == null) {
			throw new RuntimeException("Unhandled error");
		}
		return index;
	}

	abstract String doAction(M menu, Result result);

	abstract Class<M> getClassMenu();

	abstract String getControllerIndex();

	void log(String message) {
		System.out.println(message);
	}

	String scanUserInput() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	<E extends Object> void logListObject(List<E> objectList) {
		if (objectList.isEmpty()) {
			log(EMPTY);
		} else {
			for (Object object : objectList) {
				log(object.toString());
			}
		}
	}
}
