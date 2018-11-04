package com.orangeandbronze.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.orangeandbronze.domain.BalanceEntry;

public class BalanceEntryDAO implements BalanceEntryRepository {

	private static final String JDBC_URL = String
			.format("jdbc:postgresql://google/%s?socketFactory=com.google.cloud.sql.postgres.SocketFactory"
					+ "&cloudSqlInstance=%s", "eden-project-db", "edenproject-aacf3:asia-southeast1:eden-project");
	private static final String USERNAME = "eden-user";
	private static final String PASSWORD = "eden-user";

	private final Connection conn;

	public BalanceEntryDAO() {
		try {
			conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new EntryStorageException("Problem connecting to database.", e);
		}
	}

	@Override
	public List<BalanceEntry> findAll() {
		List<BalanceEntry> entries = new ArrayList<>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM balance_entry ORDER BY timestamp DESC");
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				BalanceEntry entry = new BalanceEntry(rs.getString("id"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getBigDecimal("money"), rs.getTimestamp("timestamp").toInstant());
				entries.add(entry);
			}
		} catch (SQLException e) {
			throw new EntryStorageException("Problem connecting to database.", e);
		}
		return entries;
	}

	@Override
	public void save(BalanceEntry entry) {
		try (PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO balance_entry (id, firstname, lastname, money, timestamp) VALUES (?, ?, ?, ?, ?)");) {
			stmt.setString(1, entry.getId());
			stmt.setString(2, entry.getFirstname());
			stmt.setString(3, entry.getLastname());
			stmt.setBigDecimal(4, entry.getMoney());
			stmt.setTimestamp(5,
					Timestamp.valueOf(LocalDateTime.ofInstant(entry.getTimestamp(), ZoneId.systemDefault())));
			stmt.execute();
		} catch (SQLException e) {
			throw new EntryStorageException("Problem connecting to database.", e);
		}
	}

}
