package com.anzisolutions.bankingsimulator.bankingsystem;

import com.anzisolutions.bankingsimulator.bankingsystem.exception.InsufficientFundsException;

public class Account {
	private int balance;
	private String iban;
	private String owner;

	public Account(String iban, String owner) {
		this.balance = 0;
		this.iban = iban;
		this.owner = owner;
	}
	
	public int getBalance() {
		return balance;
	}

	public void increaseBalance(int amount) {
		balance += amount;
	}
	
	public void decreaseBalance(int amount) throws InsufficientFundsException {
		if(amount > balance) {
			throw new InsufficientFundsException();
		}
		balance -= amount;
	}

	public String getIBAN() {
		return iban;
	}

	public String getOwner() {
		return owner;
	}

}
