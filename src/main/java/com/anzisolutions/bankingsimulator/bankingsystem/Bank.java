package com.anzisolutions.bankingsimulator.bankingsystem;

import java.util.ArrayList;

import com.anzisolutions.bankingsimulator.Internet;
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

	public void deposit(String personID, String iban, int amount) throws LoginFailedException {
		Account account = getAuthentiactedAccount(personID, iban);
		account.increaseBalance(amount);
	}

	public Account logInToAccount(String personID, String iban) throws LoginFailedException {
		return getAuthentiactedAccount(personID, iban);		
	}

	public void withdraw(String personID, String iban, int amount) throws LoginFailedException, InsufficientFundsException {
		Account account = getAuthentiactedAccount(personID, iban);
		account.decreaseBalance(amount);
	}
	
	public void transfer(String personID, String fromIban, String toIban, int amount) throws InsufficientFundsException, LoginFailedException {
		Account fromAccount = getAuthentiactedAccount(personID, fromIban);
		fromAccount.decreaseBalance(amount);
		int toBankID = Integer.parseInt(toIban.substring(0, AccountBook.IBAN_BANK_LENGTH));
		if(toBankID == id) {
			Account toAccount = accountBook.getAccount(toIban);
			toAccount.increaseBalance(amount);
		} else {
			Bank toBank = internet.getBanks().get(Integer.toString(toBankID));
			toBank.transfer(id, toIban, amount);
		}
	}
	
	public void transfer(int bankID, String iban, int amount) {
		Account toAccount = accountBook.getAccount(iban);
		toAccount.increaseBalance(amount);
	}
	
	private Account getAuthentiactedAccount(String personID, String iban) throws LoginFailedException {
		Account account = accountBook.getAccount(iban);
		if (!account.getOwner().equals(personID)) {
			throw new LoginFailedException();
		}		
		return account;
	}

}
