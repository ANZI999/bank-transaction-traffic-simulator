package com.anzisolutions.bankingsimulator.clientbase;

import java.util.ArrayList;

import com.anzisolutions.bankingsimulator.exception.InsufficientFundsException;

public class ClientFinances {
	private String taxID;
	private int cash;
	private int incomeTotal;
	private ArrayList<String> ownedIbans;
	
	public ClientFinances(String taxID) {
		this.taxID = taxID;
		this.cash = 0;
		this.incomeTotal = 0;
		this.ownedIbans = new ArrayList<String>();
	}

	public String getTaxID() {
		return taxID;
	}

	public int getCash() {
		return cash;
	}

	public void payday(int salary) {
		cash += salary;
		incomeTotal += salary;
	}

	public int getIncomeTotal() {
		return incomeTotal;
	}

	public ArrayList<String> getOwnedIbans() {
		return ownedIbans;
	}

	public void addOwnedIban(String iban) {
		ownedIbans.add(iban);		
	}

	public void spendCash(int amount) throws InsufficientFundsException {
		if(amount > cash) {
			throw new InsufficientFundsException();
		}
		cash -= amount;
	}

}
