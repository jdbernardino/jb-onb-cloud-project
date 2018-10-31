package com.orangeandbronze.repository;

import java.util.List;

import com.orangeandbronze.domain.BalanceEntry;

public interface BalanceEntryRepository {

	List<BalanceEntry> findAll();
	
	void save(BalanceEntry entry);
}
