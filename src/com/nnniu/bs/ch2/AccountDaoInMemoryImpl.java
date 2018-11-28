package com.nnniu.bs.ch2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoInMemoryImpl implements AccountDao {

	private Map<Long, Account> accountsMap = new HashMap<>();
	
	{
		Account account1 = new Account();
		account1.setId(1L);
		account1.setOwnerName("John");
		account1.setBalance(10.0);
		
		Account account2 = new Account();
		account2.setId(2L);
		account2.setOwnerName("Mary");
		account2.setBalance(20.0);
		
		accountsMap.put(account1.getId(), account1);
		accountsMap.put(account2.getId(), account2);
	}
	
	@Override
	public void insert(Account account) {
		accountsMap.put(account.getId(), account);
	}
	
	@Override
	public void update(Account account) {
		accountsMap.put(account.getId(), account);
	}
	
	@Override
	public void update(List<Account> accounts) {
		Iterator iter = accounts.iterator();
		while (iter.hasNext()) {
			Account acc = (Account) iter.next();
			update(acc);
		}
	}
	
	@Override
	public void delete(long accountId) {
		accountsMap.remove(accountId);
	}
	
	@Override
	public Account find(long accountId) {
		return accountsMap.get(accountId);
	}
	
	@Override
	public List<Account> find(List<Long> accountIds) {
		List<Account> accounts = new ArrayList<>();
		
		Iterator iter = accountIds.iterator();
		while (iter.hasNext()) {
			Account acc = accountsMap.get(iter.next());
			if (acc != null) {
				accounts.add(acc);
			}
		}
		
		return accounts;
	}
	
	@Override
	public List<Account> find(String ownerName) {
		List<Account> accounts = new ArrayList<>();
		
		for (Entry<Long, Account> entry : accountsMap.entrySet()) {
			Account acc = entry.getValue();
			if (acc.getOwnerName().equals(ownerName)) {
				accounts.add(acc);
			}
		}
		
		return accounts;
	}
	
	@Override
	public List<Account> find(boolean locked) {
		return null;
	}
}
