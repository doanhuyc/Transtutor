package main.controller;

import java.util.List;

import main.gui.SortMenu;
import main.model.ValidRecord;
import main.service.InventoryService;

public class SortController extends Controller<SortMenu> {
	private final InventoryService inventoryService;

	public SortController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@Override
	Class<? extends Controller> doAction(SortMenu menu) {
		switch (menu) {
			case BY_ID:
				return printList(inventoryService.sortById());
			case BY_NAME:
				return printList(inventoryService.sortByName());
			case BY_QUANTITY:
				return printList(inventoryService.sortByQuantity());
			case BY_PRICE:
				return printList(inventoryService.sortByPrice());
			case BY_STATUS:
				return printList(inventoryService.sortByStatus());
			case BACK:
				return MainMenuController.class;
			case QUIT:
			default:
				return null;
		}
	}

	@Override
	Class<SortMenu> getClassMenu() {
		return SortMenu.class;
	}

	private Class<? extends Controller> printList(List<ValidRecord> recordList) {
		logListObject(recordList);
		return getClass();
	}
}
