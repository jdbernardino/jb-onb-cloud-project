package com.orangeandbronze.service;

import java.math.BigDecimal;
import java.util.List;

public interface BalanceEntryService {

	List<BalanceEntryDTO> findAll();
	
	void save(String firstname, String lastname, BigDecimal money);
}
