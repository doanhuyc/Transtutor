package main.gui;

public interface Menu {
	static <E extends Menu> E get(Class<E> enumType, String input) {
		try {
			int ordinal = Integer.parseInt(input.trim());
			for (E menu : enumType.getEnumConstants()) {
				if (menu.getOrdinal() == (ordinal - 1)) {
					return menu;
				}
			}
			return null;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	static <E extends Menu> E[] values(Class<E> enumType) {
		return enumType.getEnumConstants();
	}

	int getOrdinal();

	String getDescription();

	default String getDescriptionToPrint() {
		return (getOrdinal() + 1) + ": " + getDescription();
	}
}
