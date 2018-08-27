package com.anzisolutions.bankingsimulator.util;

import static org.junit.Assert.assertEquals;

import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;

public class TestHelper {
	public static void assertValidIBAN(String iban, int bankID) throws Exception {
		String ibanBankSection = iban.substring(0, IBAN.BANK_LENGTH);
		int ibanBankID = Integer.parseInt(ibanBankSection);
		assertEquals(bankID, ibanBankID);
		assertEquals(IBAN.LENGTH, iban.length());
	}
}
