package com.anzisolutions.bankingsimulator.bankingsystem;

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

	public String getIBAN() {
		return iban;
	}

	public String getOwner() {
		return owner;
	}

}
