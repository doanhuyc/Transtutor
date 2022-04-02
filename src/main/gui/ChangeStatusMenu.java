package main.gui;

import main.model.Status;

public enum ChangeStatusMenu implements Menu {
	ACTIVE("Active", Status.ACTIVE),
	DISCONTINUED("Discontinued", Status.DISCONTINUED),
	RECALLED("Recalled", Status.RECALLED),
	BACK("Back", null),
	QUIT("Quit", null);

	private String description;
	private Status status;

	ChangeStatusMenu(String description, Status status) {
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
