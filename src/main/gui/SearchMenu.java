package main.gui;

public enum SearchMenu implements Menu {
	BY_ID("By id"),
	BY_NAME("By name"),
	BACK("Back"),
	QUIT("Quit");

	private String description;

	SearchMenu(String description) {
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
