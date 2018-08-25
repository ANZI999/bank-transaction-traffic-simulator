package com.anzisolutions.bankingsimulator.util;

import static org.junit.Assert.assertEquals;

import com.anzisolutions.bankingsimulator.bankingsystem.AccountBook;

public class TestHelper {
	public static void assertValidIBAN(String iban, int bankID) throws Exception {
		String ibanBankSection = iban.substring(0, AccountBook.IBAN_BANK_LENGTH);
		int ibanBankID = Integer.parseInt(ibanBankSection);
		assertEquals(bankID, ibanBankID);
		assertEquals(AccountBook.IBAN_LENGTH, iban.length());
	}
}
