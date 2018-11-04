package com.orangeandbronze.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

import com.orangeandbronze.domain.BalanceEntry;

public class BalanceEntryDAO implements BalanceEntryRepository {

	private final DataSource ds;

	public BalanceEntryDAO() {
		String jdbcUrl = String.format(
			    "jdbc:postgresql://google/%s?socketFactory=com.google.cloud.sql.postgres.SocketFactory"
			        + "&cloudSqlInstance=%s",
			    "eden-project-db",
			    "edenproject-aacf3:asia-southeast1:eden-project");
		PGSimpleDataSource ds = new PGSimpleDataSource();
		ds.setDatabaseName(jdbcUrl);
		ds.setUser("postgres");
		ds.setPassword("postgres");

		this.ds = ds;
	}

	@Override
	public List<BalanceEntry> findAll() {
		List<BalanceEntry> entries = new ArrayList<>();
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM balance_entry ORDER BY timestamp DESC");
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				BalanceEntry entry = new BalanceEntry(rs.getString("id"),
						rs.getString("firstname"),
						rs.getString("lastname"),
						rs.getBigDecimal("money"),
						rs.getTimestamp("timestamp").toInstant());
				entries.add(entry);
			}
		} catch (SQLException e) {
			throw new EntryStorageException("Problem connecting to database.", e);
		}
		return entries;
	}

	@Override
	public void save(BalanceEntry entry) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(
						"INSERT INTO balance_entry (id, firstname, lastname, money, timestamp) VALUES (?, ?, ?, ?, ?)");) {
			stmt.setString(1, entry.getId());
			stmt.setString(2, entry.getFirstname());
			stmt.setString(3, entry.getLastname());
			stmt.setBigDecimal(4, entry.getMoney());
			stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.ofInstant(entry.getTimestamp(), ZoneId.systemDefault())));
			stmt.execute();
		} catch (SQLException e) {
			throw new EntryStorageException("Problem connecting to database.", e);
		}
	}

}
