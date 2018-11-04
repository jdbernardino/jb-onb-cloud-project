package com.orangeandbronze.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

public class BalanceEntry implements Comparable<BalanceEntry> {
	
	private static final String DELIMITER = ";";

	private final String id;

	private final String firstname;

	private final String lastname;

	private final BigDecimal money;

	private final Instant timestamp;
	
	public BalanceEntry(String id, String firstname, String lastname, BigDecimal money, Instant timestamp) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.money = money;
		this.timestamp = timestamp;
	}
	
	public BalanceEntry(String firstname, String lastname, BigDecimal money, Instant timestamp) {
		this(UUID.randomUUID().toString(), firstname, lastname, money, timestamp);
	}

	public BalanceEntry(String firstname, String lastname, BigDecimal money) {
		this(firstname, lastname, money, Instant.now());
	}
	
	public String getId() {
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
	
	public Instant getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	public static BalanceEntry stringToEntity(String fromString){
		String[] info = fromString.split(DELIMITER);
		return new BalanceEntry(info[0], info[1], info[2], new BigDecimal(info[3]), ZonedDateTime.parse(info[4]).toInstant());
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
