package com.anzisolutions.bankingsimulator.bankingsystem;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class BookKeeping {
	
	private int bankID;
	private ConcurrentHashMap<IBAN, Account> accounts;
	private ConcurrentHashMap<String, ArrayList<IBAN>> userIndex;
	
	public BookKeeping(int bankID) {
		this.bankID = bankID;
		this.accounts = new ConcurrentHashMap<IBAN, Account>();
		this.userIndex = new ConcurrentHashMap<String, ArrayList<IBAN>>();
	}
	
	public Account getAccount(IBAN iban) {
		return accounts.get(iban);
	}
	
	public IBAN addAccount(String personID) {
		IBAN iban = new IBAN(bankID);
		Account account = new Account(iban, personID);
		accounts.put(iban, account);
		
		ArrayList<IBAN> userIbans;
		if (!userIndex.containsKey(personID)) {
			userIbans = userIndex.put(personID, new ArrayList<IBAN>());
		} 
		userIbans = userIndex.get(personID);
		
		userIbans.add(iban);
		
		return iban;
	}

	public ArrayList<IBAN> getUserAccounts(String personID) {
		return userIndex.get(personID);
	}

	public int getBankID() {
		return bankID;
	}

	public int getAccountTotal() {
		return accounts.values().stream()
				.map(account -> account.getBalance())
				.mapToInt(Integer::intValue).sum();
	}
}
