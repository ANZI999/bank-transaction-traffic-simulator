package com.anzisolutions.bankingsimulator.clientbase;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.exception.InsufficientFundsException;

@RunWith(SpringRunner.class)
public class ClientFinancesTest {
	
	@InjectMocks
	private ClientFinances finances;
	
	@Test
    public void payday() throws Exception {
		int salary = 300000;
		
		assertEquals(0, finances.getCash());	
		finances.payday(salary);	
		assertEquals(salary, finances.getCash());
	}
	
	@Test
    public void getIncomeTotal() throws Exception {
		int firstSalary = 300000;
		
		assertEquals(0, finances.getIncomeTotal());	
		finances.payday(firstSalary);	
		assertEquals(firstSalary, finances.getIncomeTotal());
	}
	
	@Test
    public void addAccount() throws Exception {
		String firstAccount = "firstAccount";
		String secondAccount = "secondAccount";
		
		assertEquals(0, finances.getOwnedIbans().size());	
		finances.addOwnedIban(firstAccount);	
		assertEquals(1, finances.getOwnedIbans().size());	
		finances.addOwnedIban(secondAccount);	
		assertEquals(2, finances.getOwnedIbans().size());	
	}
	
	@Test
    public void spendCash() throws Exception {
		int firstSalary = 1000;
		int spendAmount = 360;
		
		finances.payday(firstSalary);
		assertEquals(firstSalary, finances.getCash());
		finances.spendCash(spendAmount);
		assertEquals(firstSalary - spendAmount, finances.getCash());
	}
	
	@Test(expected = InsufficientFundsException.class)
    public void spendCashNotOwned() throws Exception {
		int spendAmount = 3600;
		
		finances.spendCash(spendAmount);
	}

}
