package com.anzisolutions.bankingsimulator.bankingsystem;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountBook {
	public static final int IBAN_LENGTH = 23;
	public static final int IBAN_BANK_LENGTH = 3;
	
	private int bankID;
	private HashMap<String, Account> accounts;
	private HashMap<String, ArrayList<String>> userIndex;
	
	public AccountBook(int bankID) {
		this.bankID = bankID;
		this.accounts = new HashMap<String, Account>();
		this.userIndex = new HashMap<String, ArrayList<String>>();
	}
	
	public Account getAccount(String iban) {
		return accounts.get(iban);
	}
	
	public String addAccount(String personID) {
		String iban = generateIBAN();
		Account account = new Account(iban, personID);
		accounts.put(iban, account);
		
		ArrayList<String> userIbans;
		if (!userIndex.containsKey(personID)) {
			userIbans = userIndex.put(personID, new ArrayList<String>());
		} 
		userIbans = userIndex.get(personID);
		
		userIbans.add(iban);
		
		return iban;
	}

	public ArrayList<String> getUserAccounts(String personID) {
		return userIndex.get(personID);
	}
	
	private String generateIBAN() {		
		String bankSect = String.format("%0" + IBAN_BANK_LENGTH + "d", bankID);
		String time = Long.toString(System.currentTimeMillis());
		String random = String.format("%07d", new SecureRandom().nextInt(10000000));
		return bankSect + time + random;
	}
}
