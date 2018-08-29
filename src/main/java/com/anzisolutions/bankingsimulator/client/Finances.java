package com.anzisolutions.bankingsimulator.client;

import java.util.ArrayList;

import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;
import com.anzisolutions.bankingsimulator.exception.InsufficientFundsException;

public class Finances {
	private String taxID;
	private int cash;
	private int incomeTotal;
	private ArrayList<IBAN> ownedIbans;
	
	public Finances(String taxID) {
		this.taxID = taxID;
		this.cash = 0;
		this.incomeTotal = 0;
		this.ownedIbans = new ArrayList<IBAN>();
	}

	public String getTaxID() {
		return taxID;
	}

	public int getCash() {
		return cash;
	}

	public void payday(long salary) {
		cash += salary;
		incomeTotal += salary;
	}

	public int getIncomeTotal() {
		return incomeTotal;
	}

	public ArrayList<IBAN> getOwnedIbans() {
		return ownedIbans;
	}

	public void addOwnedIban(IBAN iban) {
		ownedIbans.add(iban);		
	}

	public void spendCash(int amount) throws InsufficientFundsException {
		if(amount > cash) {
			throw new InsufficientFundsException();
		}
		cash -= amount;
	}
	
	public void addCash(int amount) {
		cash += amount;
	}

}
