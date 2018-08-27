package com.anzisolutions.bankingsimulator.bankingsystem;

import java.util.ArrayList;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.exception.InsufficientFundsException;
import com.anzisolutions.bankingsimulator.exception.LoginFailedException;

public class Bank {
	
	private int id; 
	private Internet internet;
	private AccountBook accountBook;
	
	public Bank(int id) {
		this.id = id;
		this.accountBook = new AccountBook(this.id);
	}

	public IBAN createAccount(String personID) {
		return accountBook.addAccount(personID);
	}

	public ArrayList<IBAN> getUserAccounts(String personID) {
		return accountBook.getUserAccounts(personID);
	}

	public int getID() {
		return this.id;		
	}

	public void deposit(String personID, IBAN iban, int amount) throws LoginFailedException {
		Account account = getAuthentiactedAccount(personID, iban);
		account.increaseBalance(amount);
	}

	public Account logInToAccount(String personID, IBAN iban) throws LoginFailedException {
		return getAuthentiactedAccount(personID, iban);		
	}

	public void withdraw(String personID, IBAN iban, int amount) throws LoginFailedException, InsufficientFundsException {
		Account account = getAuthentiactedAccount(personID, iban);
		account.decreaseBalance(amount);
	}
	
	public void transfer(String personID, IBAN fromIban, IBAN toIban, int amount) throws InsufficientFundsException, LoginFailedException {
		Account fromAccount = getAuthentiactedAccount(personID, fromIban);
		fromAccount.decreaseBalance(amount);
		int toBankID = toIban.getBankID();
		if(toBankID == id) {
			Account toAccount = accountBook.getAccount(toIban);
			toAccount.increaseBalance(amount);
		} else {
			Bank toBank = internet.getBank(toBankID);
			toBank.transfer(id, toIban, amount);
		}
	}
	
	public void transfer(int bankID, IBAN iban, int amount) {
		Account toAccount = accountBook.getAccount(iban);
		toAccount.increaseBalance(amount);
	}
	
	private Account getAuthentiactedAccount(String personID, IBAN iban) throws LoginFailedException {
		Account account = accountBook.getAccount(iban);
		if (!account.getOwner().equals(personID)) {
			throw new LoginFailedException();
		}		
		return account;
	}

	public void setInternet(Internet internet) {
		this.internet = internet;
		internet.publishBank(this);
	}

}
