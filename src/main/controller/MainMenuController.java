package main.controller;

import main.gui.MainMenu;
import main.service.InventoryService;

public class MainMenuController extends Controller<MainMenu> {
	private final InventoryService inventoryService;

	public MainMenuController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@Override
	Class<? extends Controller> doAction(MainMenu menu) {
		switch (menu) {
			case PRINT_ALL_VALID:
				return doPrintAllValid();
			case PRINT_ALL_INVALID:
				return doPrintAllInValid();
			case PRINT_ALL_BY_STATUS:
				return PrintByStatusController.class;
			case SEARCH_BY_ID_NAME:
				return SearchController.class;
			case PRINT_REPORT:
				return printReport();
			case SORT:
				return SortController.class;
			case BACK:
				return InputMenuController.class;
			case QUIT:
			default:
				return null;
		}
	}

	@Override
	Class<MainMenu> getClassMenu() {
		return MainMenu.class;
	}

	private Class<? extends Controller> doPrintAllValid() {
		logListObject(inventoryService.findAllRecords());
		return getClass();
	}

	private Class<? extends Controller> doPrintAllInValid() {
		logListObject(inventoryService.findAllBadRecords());
		return getClass();
	}

	private Class<? extends Controller> printReport() {
		log("Number of unique items " + inventoryService.getNumberOfUniqueItem());
		log("Total worth of the inventory " + inventoryService.getTotalWorth());
		log("Total count of all items in the inventory " + inventoryService.findAllRecords().size());

		return getClass();
	}
}
