package main.service;

import java.util.ArrayList;
import java.util.List;

import main.model.Record;
import main.model.Result;

public class ImportService {
	private final ParseService parseService = new ParseService();

	public Result importString(List<String> strings) {
		List<Record> records = new ArrayList<>();
		List<String> badRecords = new ArrayList<>();

		for (String str : strings) {
			Record record = parseService.parse(str);
			if (record != null) {
				records.add(record);
			} else {
				badRecords.add(str);
			}
		}

		return new Result(records, badRecords);
	}
}
