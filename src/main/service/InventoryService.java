package main.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import main.model.BadRecord;
import main.model.Record;
import main.model.ValidRecord;
import main.service.dao.Repository;

public class InventoryService implements Service {
	private final Repository repository;

	public InventoryService(Repository repository) {
		this.repository = repository;
	}

	public void saveRecordInUse(ValidRecord record) {
		repository.saveRecordInUse(record);
	}

	public ValidRecord getRecordInUse() {
		return repository.getRecordInUse();
	}

	public Optional<ValidRecord> findById(String id) {
		return repository.findById(id);
	}

	public Optional<ValidRecord> findByName(String name) {
		return repository.findByName(name);
	}

	public List<ValidRecord> findAllRecords() {
		return repository.findAllRecords();
	}

	public List<BadRecord> findAllBadRecords() {
		return repository.findAllBadRecords();
	}

	public void saveAll(List<Record> records) {
		for (Record record : records) {
			if (record instanceof ValidRecord) {
				repository.saveRecord((ValidRecord) record);
			} else if (record instanceof BadRecord) {
				repository.saveBadRecord((BadRecord) record);
			}
		}
	}

	public BigDecimal getTotalWorth() {
		BigDecimal total = BigDecimal.ZERO;

		for (ValidRecord record : findAllRecords()) {
			total = total.add(record.getPrice());
		}
		return total;
	}

	public int getNumberOfUniqueItem() {
		List<ValidRecord> listRecordUnique = new ArrayList<>();

		for (ValidRecord record : findAllRecords()) {
			if (listRecordUnique.contains(record)) {
				listRecordUnique.remove(record);
			} else {
				listRecordUnique.add(record);
			}
		}
		return listRecordUnique.size();
	}

	public List<ValidRecord> sortById() {
		return sort(repository.findAllRecords(), Comparator.comparing(ValidRecord::getId));
	}

	public List<ValidRecord> sortByName() {
		return sort(repository.findAllRecords(), Comparator.comparing(ValidRecord::getName));
	}

	public List<ValidRecord> sortByQuantity() {
		return sort(repository.findAllRecords(), Comparator.comparing(ValidRecord::getQuantity));
	}

	public List<ValidRecord> sortByPrice() {
		return sort(repository.findAllRecords(), Comparator.comparing(ValidRecord::getPrice));
	}

	private List<ValidRecord> sort(List<ValidRecord> records, Comparator<ValidRecord> comparator) {
		ValidRecord[] arr = records.toArray(new ValidRecord[0]);
		int n = arr.length;
		ValidRecord temp;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (comparator.compare(arr[j - 1], arr[j]) > 0) {
					//swap elements
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return Arrays.asList(arr);
	}
}
