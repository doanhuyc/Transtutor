package main.gui;

public enum RecordMenu implements Menu {
	SHOW_RECORD("Show entire record"),
	CHANGE_STATUS("Change status"),
	BACK("Back"),
	QUIT("Quit");

	private String description;

	RecordMenu(String description) {
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
