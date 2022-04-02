package main.gui;

public enum SortMenu implements Menu {
	BY_ID("Sort by Id"),
	BY_NAME("Sort by name"),
	BY_QUANTITY("Sort by quantity"),
	BY_PRICE("Sort by price"),
	BY_STATUS("Sort by status"),
	BACK("Back"),
	QUIT("Quit");

	private String description;

	SortMenu(String description) {
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
