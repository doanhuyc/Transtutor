package main.controller;

import java.util.ArrayList;
import java.util.List;

import main.gui.ChangeStatusMenu;
import main.model.Status;
import main.model.ValidRecord;
import main.service.InventoryService;

public class ChangeStatusController extends Controller<ChangeStatusMenu> {

	private final InventoryService inventoryService;

	public ChangeStatusController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@Override
	Class<? extends Controller> doAction(ChangeStatusMenu menu) {
		switch (menu) {

			case ACTIVE:
				return changeStatus(Status.ACTIVE);
			case DISCONTINUED:
				return changeStatus(Status.DISCONTINUED);
			case RECALLED:
				return changeStatus(Status.RECALLED);
			case BACK:
				return RecordMenuController.class;
			case QUIT:
			default:
				return null;
		}
	}

	@Override
	Class<ChangeStatusMenu> getClassMenu() {
		return ChangeStatusMenu.class;
	}

	@Override
	ChangeStatusMenu[] getMenus() {
		ValidRecord recordInUse = inventoryService.getRecordInUse();
		List<ChangeStatusMenu> list = new ArrayList<>();

		for (ChangeStatusMenu menu : ChangeStatusMenu.values()) {
			if (menu.getStatus() != recordInUse.getStatus()) {
				list.add(menu);
			}
		}
		return list.toArray(new ChangeStatusMenu[0]);
	}

	private Class<? extends Controller> changeStatus(Status status) {

		ValidRecord recordInUse = inventoryService.getRecordInUse();
		recordInUse.setStatus(status);
		log("Change status success");
		return SearchController.class;
	}
}
