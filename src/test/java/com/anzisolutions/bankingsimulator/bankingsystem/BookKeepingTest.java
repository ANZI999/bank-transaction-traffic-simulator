package com.anzisolutions.bankingsimulator.bankingsystem;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.anzisolutions.bankingsimulator.util.TestHelper;

@RunWith(MockitoJUnitRunner.class)
public class BookKeepingTest {
	private static final int BANK_ID = 90;
	private static final String TEST_USER = "user-5";

	@Test
    public void addOneAccountToUser() throws Exception {
		BookKeeping bookKeeping = new BookKeeping(BANK_ID);
		
		bookKeeping.addAccount(TEST_USER);
		ArrayList<IBAN> userAcounts = bookKeeping.getUserAccounts(TEST_USER);
		assertEquals(1, userAcounts.size());
		
    }
	
	@Test
    public void addThreeAccountsToUser() throws Exception {
		BookKeeping bookKeeping = new BookKeeping(BANK_ID);
		final int accountCount = 3;
		
		for(int i = 0; i < accountCount; i++) {
			bookKeeping.addAccount(TEST_USER);
		}
		
		ArrayList<IBAN> userAcounts = bookKeeping.getUserAccounts(TEST_USER);
		assertEquals(accountCount, userAcounts.size());
		
		Iterator<IBAN> it = userAcounts.iterator();
	    while (it.hasNext()) {
	    	TestHelper.assertValidIBAN(it.next().toString(), BANK_ID);
	    }	
    }
	
	@Test
    public void addFirstAccountToUserWhenOtherUsersHaveAccounts() throws Exception {
		BookKeeping bookKeeping = new BookKeeping(BANK_ID);
		bookKeeping.addAccount("user-1");
		bookKeeping.addAccount("user-2");
		bookKeeping.addAccount("user-3");
		
		bookKeeping.addAccount(TEST_USER);
		
		ArrayList<IBAN> userAcounts = bookKeeping.getUserAccounts(TEST_USER);
		assertEquals(1, userAcounts.size());
		
		Iterator<IBAN> it = userAcounts.iterator();
	    while (it.hasNext()) {
	    	TestHelper.assertValidIBAN(it.next().toString(), BANK_ID);
	    }
    }
	
	@Test
    public void getAccountTotal() throws Exception {
		BookKeeping bookKeeping = new BookKeeping(BANK_ID);
		int depositOne = 1090;
		int depositTwo = 2900;
		
		int total = depositOne;
		IBAN ibanOne = bookKeeping.addAccount("user-1");
		bookKeeping.getAccount(ibanOne).increaseBalance(depositOne);
		assertEquals(total, bookKeeping.getAccountTotal());
		
		total += depositTwo;
		IBAN ibanTwo = bookKeeping.addAccount("user-2");
		bookKeeping.getAccount(ibanTwo).increaseBalance(depositTwo);
		assertEquals(total, bookKeeping.getAccountTotal());
    }
	
}
