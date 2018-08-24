package com.anzisolutions.bankingsimulator.bankingsystem;

import java.util.ArrayList;

public class Internet {
	
	private static Internet instance;
	
	public static Internet getInsatnce() {
		if(instance == null) {
			instance = new Internet();
		}		
		return instance;
	}
	
	private ArrayList<String> ibans = new ArrayList<String>();
	
	private Internet() {}
	
	public void publishIBAN(String iban) {
		ibans.add(iban);
	}
	
	public ArrayList<String> getIBANs() {
		 return ibans;
	}

	public void reset() {
		ibans = new ArrayList<String>();
	}

}
