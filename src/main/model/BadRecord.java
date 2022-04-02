package main.model;

public class BadRecord implements Record {
	private String record;
	private String error;

	public void setRecord(String record) {
		this.record = record;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return record + " - " + error;
	}
}
