package com.orangeandbronze.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.orangeandbronze.domain.BalanceEntry;
import com.orangeandbronze.repository.BalanceEntryDAO;
import com.orangeandbronze.repository.BalanceEntryRepository;

public class BalanceEntryServiceImpl implements BalanceEntryService {

	private BalanceEntryRepository repository;

	public BalanceEntryServiceImpl() {
		if (Objects.isNull(repository)) {
			repository = new BalanceEntryDAO();
		}
	}

	@Override
	public List<BalanceEntryDTO> findAll() {
		List<BalanceEntry> entries = repository.findAll();
		return BalanceEntryDTO.convertEntities(entries);
	}

	@Override
	public void save(String firstname, String lastname, BigDecimal money) {
		BalanceEntry entry = new BalanceEntry(firstname, lastname, money);
		repository.save(entry);
	}

}
