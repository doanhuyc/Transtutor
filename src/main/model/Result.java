package main.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Result {
	private List<Record> records;

	private List<String> badRecords;

	public Result() {
	}

	public Result(List<Record> records, List<String> badRecords) {
		this.records = records;
		this.badRecords = badRecords;
	}

	public List<String> getBadRecords() {
		return Collections.unmodifiableList(badRecords);
	}

	public void setBadRecords(List<String> badRecords) {
		this.badRecords = badRecords;
	}

	public List<Record> getRecords() {
		return Collections.unmodifiableList(records);
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Result)) {
			return false;
		}
		Result result = (Result) o;
		return Objects.equals(getRecords(), result.getRecords()) &&
				Objects.equals(getBadRecords(), result.getBadRecords());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRecords(), getBadRecords());
	}
}
