package com.anzisolutions.bankingsimulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;

@RunWith(SpringRunner.class)
public class InternetImplTest {
	
	final private static IBAN ADDED_IBAN = new IBAN(1);
	
	@InjectMocks
	private InternetImpl internet;
	
	@Test
    public void publishIBANSucceedIfIsFirstAccount() throws Exception {		
		ArrayList<IBAN> noibans = internet.getIBANs();
		assertEquals(0, noibans.size());
		
		internet.publishIBAN(ADDED_IBAN);
		
		ArrayList<IBAN> ibans = internet.getIBANs();
		assertEquals(1, ibans.size());
		assertTrue(ibans.contains(ADDED_IBAN));
	}
	
	@Test
    public void addBank() throws Exception {
		HashMap<String, Bank> banks;
		TaxBureau taxBureau = new TaxBureau();
		
		banks = internet.getBanks();
		assertEquals(0, banks.size());
		
		Bank bank = new Bank(taxBureau.registerBankBookKeeping());
		internet.publishBank(bank);
		banks = internet.getBanks();
		assertEquals(1, banks.size());		
	}
}
