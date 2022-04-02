package main.service.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.model.BadRecord;
import main.model.ValidRecord;

public class Repository {
	private static final int MAX_STORAGE = 150;
	private final List<ValidRecord> records;
	private final List<BadRecord> badRecords;
	private ValidRecord recordInUse;

	public Repository() {
		this.records = new ArrayList<>();

		this.badRecords = new ArrayList<>();
	}

	public ValidRecord getRecordInUse() {
		return recordInUse;
	}

	public void saveRecordInUse(ValidRecord record) {
		this.recordInUse = record;
	}

	public void saveRecord(ValidRecord record) {
		if (records.size() > MAX_STORAGE) {
			throw new ArrayStoreException();
		}
		records.add(record);
	}

	public void saveBadRecord(BadRecord badRecord) {
		if (records.size() > MAX_STORAGE) {
			throw new ArrayStoreException();
		}
		badRecords.add(badRecord);
	}

	public Optional<ValidRecord> findById(String id) {
		for (ValidRecord record : records) {
			if (record.getId().equalsIgnoreCase(id)) {
				return Optional.of(record);
			}
		}
		return Optional.empty();
	}

	public Optional<ValidRecord> findByName(String name) {
		for (ValidRecord record : records) {
			if (record.getName().equalsIgnoreCase(name)) {
				return Optional.of(record);
			}
		}
		return Optional.empty();
	}

	public List<ValidRecord> findAllRecords() {
		return records;
	}

	public List<BadRecord> findAllBadRecords() {
		return badRecords;
	}
}
