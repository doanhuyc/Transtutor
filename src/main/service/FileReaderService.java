package main.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.model.BadRecord;
import main.model.Record;
import main.model.Status;
import main.model.ValidRecord;

public class FileReaderService implements Service {
	private static final String SEPARATOR = "\t";

	private static final int ID_LENGTH = 5;
	private static final String SPACE = " ";
	private static final String ALPHA_NUMERIC_REGEX = "^[a-zA-Z0-9]*$";
	private static final String ALPHA_REGEX = "^[a-zA-Z]*$";

	public List<Record> readFileFromPath(String str) throws IOException {
		Path path = Paths.get(str);

		String file = Files.readString(path);
		return readString(file);
	}

	public List<Record> readString(String str) {
		String[] lines = str.split(SEPARATOR);
		return parseStrings(Arrays.asList(lines));
	}

	private List<Record> parseStrings(List<String> strings) {
		List<Record> recordList = new ArrayList<>();
		for (String str : strings) {
			Record record = parse(str);
			recordList.add(record);
		}
		return recordList;
	}

	private Record parse(String str) {
		BadRecord badRecord = new BadRecord();
		badRecord.setRecord(str);

		if (str == null || str.isEmpty()) {
			badRecord.setError("String is null or empty");
			return badRecord;
		}

		String[] strings = str.split(SPACE);
		if (strings.length != 3) {
			badRecord.setError("String length is less than 3 chars");
			return badRecord;
		}

		String idName = strings[0];
		String quantityStr = strings[1];
		String priceStr = strings[2];

		if (isValidIdName(idName)) {
			String id = idName.substring(0, ID_LENGTH);
			String name = idName.substring(ID_LENGTH);

			if (!isValidName(name)) {
				badRecord.setError("Name invalid");
				return badRecord;
			}

			if (!isValidId(id)) {
				badRecord.setError("id invalid");
				return badRecord;
			}

			if (!isValidQuantity(quantityStr)) {
				badRecord.setError("quantity invalid");
				return badRecord;
			}

			if (!isValidBigDecimal(priceStr)) {
				badRecord.setError("price invalid");
				return badRecord;
			}

			int quantity = Integer.parseInt(quantityStr);
			BigDecimal price = new BigDecimal(priceStr);
			return new ValidRecord(id, name, quantity, price, Status.ACTIVE);
		} else {
			badRecord.setError("Invalid idName");
			return badRecord;
		}
	}

	private boolean isValidIdName(String str) {
		return str.length() > ID_LENGTH;
	}

	private boolean isValidId(String str) {
		return str.matches(ALPHA_NUMERIC_REGEX);
	}

	private boolean isValidName(String str) {
		return str.matches(ALPHA_REGEX);
	}

	private boolean isValidQuantity(String str) {
		try {
			int quantity = Integer.parseInt(str);
			return quantity >= 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isValidBigDecimal(String str) {
		try {
			BigDecimal price = new BigDecimal(str);
			return price.compareTo(BigDecimal.ZERO) >= 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
