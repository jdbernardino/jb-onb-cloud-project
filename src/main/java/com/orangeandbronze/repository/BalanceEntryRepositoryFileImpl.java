package com.orangeandbronze.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.orangeandbronze.domain.BalanceEntry;

public class BalanceEntryRepositoryFileImpl implements BalanceEntryRepository {
	
	private static final String FILENAME = "SampleRepo.txt";
	private static final File FILE_REPO = new File(FILENAME);

	@Override
	public List<BalanceEntry> findAll() {
		List<BalanceEntry> entries = new ArrayList<>();
		if(!FILE_REPO.exists() || !FILE_REPO.isFile()){
			return entries;
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
			entries = reader.lines().map(BalanceEntry::stringToEntity).collect(Collectors.toList());
		} catch (IOException e) {
			throw new EntryStorageException("There is a problem reading " + FILENAME, e);
		}
		
		return entries;
	}

	@Override
	public void save(BalanceEntry entry) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, FILE_REPO.exists() && FILE_REPO.isFile()))){
			writer.println(entry);
		} catch (IOException e) {
			throw new EntryStorageException("There is a problem writing to " + FILENAME, e);
		}
	}

}
