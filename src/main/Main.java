package main;

import main.controller.distributor.ControllerDistributor;
import main.test.InventoryFileBuilder;

public class Main {
	private static final String INVENTORY_FILE_PATH = "src/inventoryFile.txt";

	private static final ControllerDistributor controllerDistributor = new ControllerDistributor();

	public static void main(String[] args) {
		InventoryFileBuilder.createFile(INVENTORY_FILE_PATH);
		controllerDistributor.doAction();
	}
}
