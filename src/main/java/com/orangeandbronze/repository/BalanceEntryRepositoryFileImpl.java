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
	
	private static final String FILENAME = "repo/SampleRepo.txt";
	private static final File REPO_FILE = new File(FILENAME);

	@Override
	public List<BalanceEntry> findAll() {
		List<BalanceEntry> entries = new ArrayList<>();
		if(!isRepoFileExisting()){
			return entries;
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(REPO_FILE))) {
			entries = reader.lines().map(BalanceEntry::stringToEntity).collect(Collectors.toList());
		} catch (IOException e) {
			throw new EntryStorageException("There is a problem reading " + FILENAME, e);
		}
		
		return entries;
	}

	@Override
	public void save(BalanceEntry entry) {
		if(!isRepoFileExisting()){
			setupRepoFile();
		}
		
		try (PrintWriter writer = new PrintWriter(new FileWriter(REPO_FILE, isRepoFileExisting()))){
			writer.println(entry);
		} catch (IOException e) {
			throw new EntryStorageException("There is a problem writing to " + FILENAME, e);
		}
	}
	
	private boolean isRepoFileExisting(){
		return REPO_FILE.exists() && REPO_FILE.isFile();
	}
	
	private void setupRepoFile(){
		try {
			REPO_FILE.setReadable(true);
			REPO_FILE.setWritable(true);
			REPO_FILE.createNewFile();
		} catch (IOException e) {
			throw new EntryStorageException("There is a problem setting up " + FILENAME, e);
		}
	}

}
