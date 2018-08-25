package com.anzisolutions.bankingsimulator.bankingsystem;

import java.util.ArrayList;

import com.anzisolutions.bankingsimulator.bankingsystem.exception.InsufficientFundsException;
import com.anzisolutions.bankingsimulator.bankingsystem.exception.LoginFailedException;

public class Bank {
	private static int bankCount = 0;
	
	private int id; 
	private Internet internet;
	private AccountBook accountBook;
	
	public Bank(Internet internet) {
		this.id = ++bankCount;
		this.internet = internet;
		this.accountBook = new AccountBook(this.id);
	}

	public String createAccount(String personID) {
		String iban = accountBook.addAccount(personID);
		internet.publishIBAN(iban);
		return iban;
	}

	public ArrayList<String> getUserAccounts(String personID) {
		return accountBook.getUserAccounts(personID);
	}

	public int getID() {
		return this.id;		
	}

	public void depositMoney(String personID, String iban, int amount) throws LoginFailedException {
		Account account = getAuthentiactedAccount(personID, iban);
		account.increaseBalance(amount);
	}

	public Account logInToAccount(String personID, String iban) throws LoginFailedException {
		return getAuthentiactedAccount(personID, iban);		
	}

	public void withdrawMoney(String personID, String iban, int amount) throws LoginFailedException, InsufficientFundsException {
		Account account = getAuthentiactedAccount(personID, iban);
		account.decreaseBalance(amount);
	}
	
	private Account getAuthentiactedAccount(String personID, String iban) throws LoginFailedException {
		Account account = accountBook.getAccount(iban);
		if (!account.getOwner().equals(personID)) {
			throw new LoginFailedException();
		}		
		return account;
	}

}
