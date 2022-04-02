package main.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ValidRecord implements Record {
	private String id;
	private String name;
	private int quantity;
	private BigDecimal price;
	private Status status;

	public ValidRecord(String id, String name, int quantity, BigDecimal price, Status status) {
		this.id = id.toLowerCase();
		this.name = name.toLowerCase();
		this.quantity = quantity;
		this.price = price;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ValidRecord)) {
			return false;
		}
		ValidRecord record = (ValidRecord) o;
		return getQuantity() == record.getQuantity() &&
				Objects.equals(getId(), record.getId()) &&
				Objects.equals(getName(), record.getName()) &&
				Objects.equals(getPrice(), record.getPrice()) &&
				getStatus() == record.getStatus();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getQuantity(), getPrice(), getStatus());
	}

	@Override
	public String toString() {
		return id + " " + name + " " + quantity + " " + price + " " + status;
	}
}
