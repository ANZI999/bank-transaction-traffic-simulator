package com.anzisolutions.bankingsimulator.clientbase.decision;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.TaxBureau;
import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;
import com.anzisolutions.bankingsimulator.clientbase.ClientFinances;
import com.anzisolutions.bankingsimulator.util.TestHelper;

@RunWith(SpringRunner.class)
public class CreateAccountDecisionTest {
	
	@Mock
	ClientFinances finances;
	
	@Mock
	Internet internet;
	
	@Mock
	Random randomness;

	Decision decision;
	
	@Before
	public void setUp() {
		decision = new CreateAccountDecision(randomness);
	}
	
	@Test
	public void execute() throws Exception {
		ArgumentCaptor<IBAN> captor = ArgumentCaptor.forClass(IBAN.class);
		
		Bank bank = new TaxBureau().createBank();
		int bankID = bank.getID();
		HashMap<String, Bank> banks = new HashMap<String, Bank>();
		banks.put(Integer.toString(bank.getID()), bank);
		when(internet.getBanks()).thenReturn(banks);
		when(finances.getTaxID()).thenReturn("new-person");
		
		decision.execute(internet, finances);
		verify(internet, times(1)).publishIBAN(captor.capture());
		verify(finances, times(1)).addOwnedIban(captor.capture());
		List<IBAN> ibans = captor.getAllValues();
		assertEquals(ibans.get(0), ibans.get(1));
		TestHelper.assertValidIBAN(ibans.get(0).toString(), bankID);
	}
}
