package main.controller;

import java.util.List;
import java.util.Scanner;

import main.gui.Menu;

public abstract class Controller<M extends Menu> {
	private static final String EMPTY = "******Empty*******";

	public Class<? extends Controller> process() {
		for (M menu : getMenus()) {
			log(menu.getDescriptionToPrint());
		}

		String choice = scanUserInput();

		M menu = Menu.get(getClassMenu(), choice);
		if (menu == null) {
			log("Choice is invalid");
			return getClass();
		}
		return doAction(menu);
	}

	abstract Class<? extends Controller> doAction(M menu);

	abstract Class<M> getClassMenu();

	M[] getMenus() {
		return Menu.values(getClassMenu());
	}

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
