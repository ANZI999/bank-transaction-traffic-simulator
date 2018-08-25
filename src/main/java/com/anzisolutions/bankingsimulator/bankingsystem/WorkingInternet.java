package com.anzisolutions.bankingsimulator.bankingsystem;

import java.util.ArrayList;

public class WorkingInternet implements Internet {
	
	private static WorkingInternet instance;
	
	public static WorkingInternet getInsatnce() {
		if(instance == null) {
			instance = new WorkingInternet();
		}		
		return instance;
	}
	
	private ArrayList<String> ibans = new ArrayList<String>();
	
	private WorkingInternet() {}
	
	@Override
	public void publishIBAN(String iban) {
		ibans.add(iban);
	}
	
	@Override
	public ArrayList<String> getIBANs() {
		 return ibans;
	}

	public void reset() {
		ibans = new ArrayList<String>();
	}

}
