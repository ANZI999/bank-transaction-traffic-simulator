package com.anzisolutions.bankingsimulator.bankingsystem;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkingInternet implements Internet {
	
	private static WorkingInternet instance;
	
	public static WorkingInternet getInsatnce() {
		if(instance == null) {
			instance = new WorkingInternet();
		}		
		return instance;
	}
	
	private ArrayList<String> ibans = new ArrayList<String>();
	private HashMap<String, Bank> banks = new HashMap<String, Bank>();
	
	private WorkingInternet() {}
	
	@Override
	public void publishIBAN(String iban) {
		ibans.add(iban);
	}
	
	@Override
	public ArrayList<String> getIBANs() {
		 return ibans;
	}

	@Override
	public HashMap<String, Bank> getBanks() {
		return banks;
	}
	
	@Override
	public void createBank() {
		Bank bank = new Bank(this);
		banks.put(Integer.toString(bank.getID()), bank);
	}
	
	public void reset() {
		ibans = new ArrayList<String>();
	}
}
