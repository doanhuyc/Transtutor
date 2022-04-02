package main;

import main.controller.distributor.ControllerDistributor;
import main.service.InventoryFileBuilderService;

public class Main {
	private static final InventoryFileBuilderService inventoryFileService = new InventoryFileBuilderService();
	private static final ControllerDistributor controllerDistributor = new ControllerDistributor();

	public static void main(String[] args) {
		inventoryFileService.createFile();

		controllerDistributor.doAction();
	}
}
