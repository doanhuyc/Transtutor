package main.gui;

public enum InputMenuFile implements Menu {

	INPUT_STRING("Input a string"),
	INPUT_PATH("Input a path"),
	QUIT("Quit");

	private String description;

	InputMenuFile(String description) {
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
