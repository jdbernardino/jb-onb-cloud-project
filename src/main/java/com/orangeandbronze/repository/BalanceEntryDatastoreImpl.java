package com.orangeandbronze.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.orangeandbronze.domain.BalanceEntry;

public class BalanceEntryDatastoreImpl implements BalanceEntryRepository {

	private Datastore datastore;
	private KeyFactory keyFactory;

	public BalanceEntryDatastoreImpl() {
		datastore = DatastoreOptions.getDefaultInstance().getService();
		keyFactory = datastore.newKeyFactory().setKind("balance_entry");
	}

	@Override
	public List<BalanceEntry> findAll() {
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("balance_entry")
				.setOrderBy(OrderBy.desc("timestamp")).build();
		QueryResults<Entity> resultList = datastore.run(query);
		return entitiesToEntries(resultList);
	}

	@Override
	public void save(BalanceEntry entry) {
		IncompleteKey key = keyFactory.newKey();
		FullEntity<IncompleteKey> incEntryEntity = Entity.newBuilder(key).set("firstname", entry.getFirstname())
				.set("lastname", entry.getLastname()).set("money", entry.getMoney().toString())
				.set("timestamp", entry.getTimestamp().toString()).build();
		datastore.add(incEntryEntity);
	}
	
	private BalanceEntry entityToEntry(Entity entity){
		return new BalanceEntry(entity.getKey().getId(), entity.getString("firstname"), entity.getString("lastname"), new BigDecimal(entity.getString("money")), entity.getString("timestamp"));
	}
	
	private List<BalanceEntry> entitiesToEntries(QueryResults<Entity> resultList){
		List<BalanceEntry> resultEntries = new ArrayList<>();
		while (resultList.hasNext()) {
			resultEntries.add(entityToEntry(resultList.next()));
			
		}
		return resultEntries;
	}

}
