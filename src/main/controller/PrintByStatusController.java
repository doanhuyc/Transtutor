package main.controller;

import java.util.ArrayList;
import java.util.List;

import main.gui.PrintByStatusMenu;
import main.model.Status;
import main.model.ValidRecord;
import main.service.InventoryService;

public class PrintByStatusController extends Controller<PrintByStatusMenu> {
	private final InventoryService inventoryService;

	public PrintByStatusController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@Override
	Class<? extends Controller> doAction(PrintByStatusMenu menu) {
		switch (menu) {
			case ACTIVE:
				return printByStatus(Status.ACTIVE);
			case DISCONTINUED:
				return printByStatus(Status.DISCONTINUED);
			case RECALLED:
				return printByStatus(Status.RECALLED);
			case BACK:
				return MainMenuController.class;
			case QUIT:
			default:
				return null;
		}
	}

	@Override
	Class<PrintByStatusMenu> getClassMenu() {
		return PrintByStatusMenu.class;
	}

	private Class<? extends Controller> printByStatus(Status status) {
		List<ValidRecord> listRecords = new ArrayList<>();

		for (ValidRecord record : inventoryService.findAllRecords()) {
			if (record.getStatus() == status) {
				listRecords.add(record);
			}
		}
		logListObject(listRecords);
		return getClass();
	}
}
