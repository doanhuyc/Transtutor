package main.gui;

import main.model.Status;

public enum PrintByStatusMenu implements Menu {
	ACTIVE("Print all Active items", Status.ACTIVE),
	DISCONTINUED("Print all Discontinued items", Status.DISCONTINUED),
	RECALLED("Print all Recalled items", Status.RECALLED),
	BACK("Back", null),
	QUIT("Quit", null);

	private String description;
	private Status status;

	PrintByStatusMenu(String description, Status status) {
		this.description = description;
		this.status = status;
	}

	@Override
	public int getOrdinal() {
		return ordinal();
	}

	@Override
	public String getDescription() {
		return description;
	}

	public Status getStatus() {
		return status;
	}
}
