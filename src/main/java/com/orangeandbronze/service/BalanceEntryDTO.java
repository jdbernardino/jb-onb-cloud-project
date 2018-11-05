package com.orangeandbronze.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.orangeandbronze.domain.BalanceEntry;

public class BalanceEntryDTO implements Serializable{

	private String firstname;

	private String lastname;

	private String money;

	private BalanceEntryDTO(String firstname, String lastname, BigDecimal money) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.money = money.toString();
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getMoney() {
		return money;
	}

	public static BalanceEntryDTO convertEntity(BalanceEntry entry) {
		return new BalanceEntryDTO(entry.getFirstname(), entry.getLastname(), entry.getMoney());
	}

	public static List<BalanceEntryDTO> convertEntities(List<BalanceEntry> entries) {
		return entries.stream().map(BalanceEntryDTO::convertEntity).collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return firstname + " " + lastname + " : " + money;
	}
}
