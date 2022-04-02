package main.controller;

import main.gui.RecordMenu;
import main.model.ValidRecord;
import main.service.InventoryService;

public class RecordMenuController extends Controller<RecordMenu> {
	private final InventoryService inventoryService;

	public RecordMenuController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@Override
	Class<? extends Controller> doAction(RecordMenu menu) {
		switch (menu) {
			case SHOW_RECORD:
				return showRecord();
			case CHANGE_STATUS:
				return ChangeStatusController.class;
			case BACK:
				return SearchController.class;
			case QUIT:
			default:
				return null;
		}
	}

	@Override
	Class<RecordMenu> getClassMenu() {
		return RecordMenu.class;
	}

	private Class<? extends Controller> showRecord() {
		ValidRecord recordInUse = inventoryService.getRecordInUse();
		log(recordInUse.toString());
		return getClass();
	}
}
