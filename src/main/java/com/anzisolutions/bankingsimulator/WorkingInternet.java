package com.anzisolutions.bankingsimulator;

import java.util.ArrayList;
import java.util.HashMap;

import com.anzisolutions.bankingsimulator.bankingsystem.Bank;

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
	public void publishBank(Bank bank) {
		banks.put(Integer.toString(bank.getID()), bank);
	}

	@Override
	public HashMap<String, Bank> getBanks() {
		return banks;
	}
	
	public void reset() {
		ibans = new ArrayList<String>();
	}
}
