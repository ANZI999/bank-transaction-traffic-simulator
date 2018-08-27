package com.anzisolutions.bankingsimulator.bankingsystem;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountBook {
	public static final int IBAN_LENGTH = 23;
	public static final int IBAN_BANK_LENGTH = 3;
	
	private int bankID;
	private HashMap<IBAN, Account> accounts;
	private HashMap<String, ArrayList<IBAN>> userIndex;
	
	public AccountBook(int bankID) {
		this.bankID = bankID;
		this.accounts = new HashMap<IBAN, Account>();
		this.userIndex = new HashMap<String, ArrayList<IBAN>>();
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
}
