package com.anzisolutions.bankingsimulator.bankingsystem;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.anzisolutions.bankingsimulator.util.TestHelper;

@RunWith(MockitoJUnitRunner.class)
public class AccountBookTest {
	private static final int BANK_ID = 90;
	private static final String TEST_USER = "user-5";

	@Test
    public void addOneAccountToUser() throws Exception {
		AccountBook accountBook = new AccountBook(BANK_ID);
		
		accountBook.addAccount(TEST_USER);
		ArrayList<IBAN> userAcounts = accountBook.getUserAccounts(TEST_USER);
		assertEquals(1, userAcounts.size());
		
    }
	
	@Test
    public void addThreeAccountsToUser() throws Exception {
		AccountBook accountBook = new AccountBook(BANK_ID);
		final int accountCount = 3;
		
		for(int i = 0; i < accountCount; i++) {
			accountBook.addAccount(TEST_USER);
		}
		
		ArrayList<IBAN> userAcounts = accountBook.getUserAccounts(TEST_USER);
		assertEquals(accountCount, userAcounts.size());
		
		Iterator<IBAN> it = userAcounts.iterator();
	    while (it.hasNext()) {
	    	TestHelper.assertValidIBAN(it.next().toString(), BANK_ID);
	    }	
    }
	
	@Test
    public void addFirstAccountToUserWhenOtherUsersHaveAccounts() throws Exception {
		AccountBook accountBook = new AccountBook(BANK_ID);
		accountBook.addAccount("user-1");
		accountBook.addAccount("user-2");
		accountBook.addAccount("user-3");
		
		accountBook.addAccount(TEST_USER);
		
		ArrayList<IBAN> userAcounts = accountBook.getUserAccounts(TEST_USER);
		assertEquals(1, userAcounts.size());
		
		Iterator<IBAN> it = userAcounts.iterator();
	    while (it.hasNext()) {
	    	TestHelper.assertValidIBAN(it.next().toString(), BANK_ID);
	    }
    }
	
}
