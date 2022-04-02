package main.test;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class InventoryFileBuilder {
	private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String NUMERIC = "0123456789";
	private static final String ALPHA_NUMERIC = NUMERIC + ALPHA;
	private static final String SYMBOL = "!@$%^&*()_";
	private static final Random random = new Random();

	public static void createFile(String path) {
		try {
			FileWriter myWriter = new FileWriter(path);

			createSomeRedundantItems(myWriter);

			for (int i = 0; i < 100; i++) {
				myWriter.write(createRandomRecord(true));
				myWriter.write("\t");
			}

			for (int i = 0; i < 100; i++) {
				myWriter.write(createRandomRecord(false));
				myWriter.write("\t");
			}
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void createSomeRedundantItems(FileWriter myWriter) throws IOException {
		String randomRecord = createRandomRecord(true);

		for (int i = 0; i < 50; i++) {
			myWriter.write(randomRecord);
			myWriter.write("\t");
		}
	}

	private static String createRandomRecord(boolean valid) {
		return randomIdName(valid) + " " + randomQuantity(valid) + " " + randomPrice(valid);
	}

	private static String randomQuantity(boolean valid) {
		if (valid) {
			return String.valueOf(generateRandomIntegerFromRange(0, 100));
		}
		return String.valueOf(generateRandomIntegerFromRange(-100, -1));
	}

	private static String randomPrice(boolean valid) {
		if (valid) {
			return String.valueOf(generateRandomBigDecimalFromRange(BigDecimal.ZERO, new BigDecimal(100)));
		}
		return String.valueOf(generateRandomBigDecimalFromRange(new BigDecimal(-100), new BigDecimal(-1)));
	}

	private static String randomIdName(boolean valid) {
		if (valid) {
			return randomId(true) + randomName(true);
		}

		boolean isIdNameLessThan5Char = random.nextBoolean();

		if (isIdNameLessThan5Char) {
			return randomString(generateRandomIntegerFromRange(1, 4), ALPHA_NUMERIC);
		}
		return randomId(false) + randomName(false);
	}

	private static String randomId(boolean valid) {
		if (valid) {
			return randomString(5, ALPHA_NUMERIC);
		}
		return randomString(4, ALPHA_NUMERIC) + randomString(1, SYMBOL);
	}

	private static String randomName(boolean valid) {
		if (valid) {
			return randomString(10, ALPHA);
		}
		return randomString(4, ALPHA) + randomString(1, SYMBOL) + randomString(3, NUMERIC);
	}

	private static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
		BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
		return randomBigDecimal.setScale(2, RoundingMode.HALF_UP);
	}

	private static int generateRandomIntegerFromRange(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	private static String randomString(int len, String typeString) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(typeString.charAt(generateRandomIntegerFromRange(0, typeString.length() - 1)));
		}
		return sb.toString();
	}
}
