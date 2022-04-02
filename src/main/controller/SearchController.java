package main.controller;

import main.gui.SearchMenu;
import main.model.ValidRecord;
import main.service.InventoryService;

public class SearchController extends Controller<SearchMenu> {
	private final InventoryService inventoryService;

	public SearchController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@Override
	Class<? extends Controller> doAction(SearchMenu menu) {
		switch (menu) {

			case BY_ID:
				return findById();
			case BY_NAME:
				return findByName();
			case BACK:
				return MainMenuController.class;
			case QUIT:
			default:
				return null;
		}
	}

	@Override
	Class<SearchMenu> getClassMenu() {
		return SearchMenu.class;
	}

	private Class<? extends Controller> findById() {
		log("Id to search (case non-sensitive)");
		String string = scanUserInput();

		ValidRecord rec = inventoryService.findById(string).orElse(null);
		return redirect(rec);
	}

	private Class<? extends Controller> findByName() {
		log("Name to search (case non-sensitive)");
		String string = scanUserInput();

		ValidRecord rec = inventoryService.findByName(string).orElse(null);
		return redirect(rec);
	}

	private Class<? extends Controller> redirect(ValidRecord rec) {
		if (rec != null) {
			log("Record found");
			inventoryService.saveRecordInUse(rec);
			return RecordMenuController.class;
		} else {
			log("No record found");
			return getClass();
		}
	}
}
