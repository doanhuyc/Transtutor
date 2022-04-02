package main.gui;

public enum MainMenu implements Menu {
	PRINT_ALL_VALID("Print all items in the inventory unsorted"),
	PRINT_ALL_INVALID("Print invalid records from an error file"),
	PRINT_ALL_BY_STATUS("Print all items by status"),
	SEARCH_BY_ID_NAME("Search for an item by Id or Name"),
	PRINT_REPORT("Print report"),
	SORT("Sort"),
	BACK("Back"),
	QUIT("Quit");

	private String description;

	MainMenu(String description) {
		this.description = description;
	}

	@Override
	public int getOrdinal() {
		return ordinal();
	}

	@Override
	public String getDescription() {
		return description;
	}
}