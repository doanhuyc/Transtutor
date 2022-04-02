package main.service;

import java.math.BigDecimal;
import java.util.List;

import main.model.Record;
import main.model.Status;

class ParseService {
	private static class TestParseService {
		private static final ParseService parseService = new ParseService();

		static void test() {
			testStrNull();
			testStrEmpty();
			testStrInvalidFormat();
			testStrInvalidIdName();
			testStrInvalidId();
			testStrInvalidName();
			testStrInvalidQuantity();
			testStrInvalidBigDecimal();
			testStrValid();
		}

		static void printResult(Class clazz, String method, boolean result) {
			System.out.println(clazz + "_" + method + " : " + result);
		}

		private static void testStrNull() {
			//given
			//when
			Record actual = parseService.parse(null);
			//then
			printResult(new Object() {
			}.getClass().getEnclosingMethod().getName(), actual == null);
		}

		private static void testStrEmpty() {
			//given
			//when
			Record actual = parseService.parse("");
			//then
			printResult(new Object() {
			}.getClass().getEnclosingMethod().getName(), actual == null);
		}

		private static void testStrInvalidFormat() {
			//given
			//when
			Record actual = parseService.parse("9971xStroLLer 25 134.78    t");
			//then
			printResult(new Object() {
			}.getClass().getEnclosingMethod().getName(), actual == null);
		}

		private static void testStrInvalidIdName() {
			//given
			//when
			Record actual = parseService.parse("9971 25 134.78");
			//then
			printResult(new Object() {
			}.getClass().getEnclosingMethod().getName(), actual == null);
		}

		private static void testStrInvalidId() {
			//given
			//when
			Record actual = parseService.parse("9971&StroLLer 25 134.78");
			//then
			printResult(new Object() {
			}.getClass().getEnclosingMethod().getName(), actual == null);
		}

		private static void testStrInvalidName() {

			//given
			List<String> listGivenStr = List.of("9971xStroLLer123 25 134.78", "9971xStroLLer@# 25 134.78", "9971xStro*LLer 245 134.78");
			//when
			for (int i = 0; i < listGivenStr.size(); i++) {
				Record actual = parseService.parse("9971xStroLLer 25xxx 134.78");
				//then
				printResult(new Object() {
				}.getClass().getEnclosingMethod().getName() + i, actual == null);
			}
		}

		private static void testStrInvalidQuantity() {
			//given
			List<String> listGivenStr = List.of("9971xStroLLer 25xxx 134.78", "9971xStroLLer -1 134.78", "9971xStroLLer 2x45 134.78");
			//when
			for (int i = 0; i < listGivenStr.size(); i++) {
				Record actual = parseService.parse("9971xStroLLer 25xxx 134.78");
				//then
				printResult(new Object() {
				}.getClass().getEnclosingMethod().getName() + i, actual == null);
			}
		}

		private static void testStrInvalidBigDecimal() {
			//given
			List<String> listGivenStr = List.of("9971xStroLLer 25 134.78x", "9971xStroLLer 25 -134.78", "9971xStroLLer 25 134,78");
			//when
			for (int i = 0; i < listGivenStr.size(); i++) {
				Record actual = parseService.parse("9971xStroLLer 25xxx 134.78");
				//then
				printResult(new Object() {
				}.getClass().getEnclosingMethod().getName() + i, actual == null);
			}
		}

		private static void testStrValid() {
			//given
			Record expected = new Record("9971x", "StroLLer", 25, new BigDecimal("134.78"), Status.ACTIVE);
			//when
			Record actual = parseService.parse("9971xStroLLer 25 134.78");
			//then
			printResult(new Object() {
			}.getClass().getEnclosingMethod().getName(), actual.equals(expected));
		}

		private static void printResult(String method, boolean result) {
			printResult(TestParseService.class, method, result);
			if (!result) {
				throw new IllegalArgumentException();
			}
		}
	}

	private static final int ID_LENGTH = 5;
	private static final String SPACE = " ";
	private static final String ALPHA_NUMERIC_REGEX = "^[a-zA-Z0-9]*$";
	private static final String ALPHA_REGEX = "^[a-zA-Z]*$";

	/**
	 *
	 * @param str
	 * @return Record if the String is valid else return null
	 */
	Record parse(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}

		String[] strings = str.split(SPACE);
		if (strings.length != 3) {
			return null;
		}

		String idName = strings[0];
		String quantityStr = strings[1];
		String priceStr = strings[2];

		if (isValidIdName(idName)) {
			String id = idName.substring(0, ID_LENGTH);
			String name = idName.substring(ID_LENGTH);

			if (isValidName(name) && isValidId(id) && isValidQuantity(quantityStr) && isValidBigDecimal(priceStr)) {
				int quantity = Integer.parseInt(quantityStr);
				BigDecimal price = new BigDecimal(priceStr);
				return new Record(id, name, quantity, price, Status.ACTIVE);
			}
		}

		return null;
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
