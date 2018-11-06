package com.orangeandbronze.domain;

import java.math.BigDecimal;

import com.google.cloud.Timestamp;

public class BalanceEntry implements Comparable<BalanceEntry> {
	
	private static final String DELIMITER = ";";

	private final Long id;

	private final String firstname;

	private final String lastname;

	private final BigDecimal money;

	private final Timestamp timestamp;
	
	public BalanceEntry(Long id, String firstname, String lastname, BigDecimal money, Timestamp timestamp) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.money = money;
		this.timestamp = timestamp;
	}
	
	public BalanceEntry(String firstname, String lastname, BigDecimal money, Timestamp timestamp) {
		this(0L, firstname, lastname, money, timestamp);
	}

	public BalanceEntry(String firstname, String lastname, BigDecimal money) {
		this(firstname, lastname, money, Timestamp.now());
	}
	
	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public BigDecimal getMoney() {
		return money;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BalanceEntry other = (BalanceEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(BalanceEntry o) {
		return this.timestamp.compareTo(o.timestamp);
	}
	
	@Override
	public String toString() {
		return id + DELIMITER + firstname + DELIMITER + lastname + DELIMITER + money + DELIMITER + timestamp;
	}

}
