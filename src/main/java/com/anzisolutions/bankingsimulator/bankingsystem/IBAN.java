package com.anzisolutions.bankingsimulator.bankingsystem;

import java.security.SecureRandom;

public class IBAN {
	public static final int LENGTH = 23;
	public static final int BANK_LENGTH = 3;
	
	private String value;
	private int bankID;
	
	public IBAN(int bankID) {
		this.bankID = bankID;
		this.value = generateValue();
	}
	
	public int getBankID() {
		return bankID;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	private String generateValue() {		
		String bankSect = String.format("%0" + BANK_LENGTH + "d", bankID);
		String time = Long.toString(System.currentTimeMillis());
		String random = String.format("%07d", new SecureRandom().nextInt(10000000));
		return bankSect + time + random;
	}
}
