package main.gui;

public interface Menu {
	static <E extends Menu> E get(Class<E> enumType, String input) {
		try {
			int ordinal = Integer.parseInt(input);
			for (E menu : enumType.getEnumConstants()) {
				if (menu.getOrdinal() == ordinal) {
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

	abstract int getOrdinal();

	abstract String getDescription();
}
