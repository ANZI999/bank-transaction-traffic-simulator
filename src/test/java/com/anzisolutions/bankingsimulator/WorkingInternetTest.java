package com.anzisolutions.bankingsimulator;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.bankingsystem.Bank;

@RunWith(SpringRunner.class)
public class WorkingInternetTest {
	
	final private static String ADDED_IBAN = "dfsdfsdffdsf";
	
	private WorkingInternet internet = WorkingInternet.getInsatnce();
	
	@Before 
	public void setup() {
		internet.reset();
	}
	
	@Test
    public void getInstanceReturnsItself() throws Exception {
		WorkingInternet internet = WorkingInternet.getInsatnce();
		assertThat(internet, instanceOf(WorkingInternet.class));	
	}
	
	@Test
	public void constructorsAreAllPrivate() throws Exception {
		Constructor[] constructors = WorkingInternet.class.getDeclaredConstructors();
		for(int i = 0; i < constructors.length; i++) {
			assertTrue(Modifier.isPrivate(constructors[i].getModifiers()));
		}
	}
	
	@Test
    public void publishIBANSucceedIfIsFirstAccount() throws Exception {		
		ArrayList<String> noibans = internet.getIBANs();
		assertEquals(0, noibans.size());
		
		internet.publishIBAN(ADDED_IBAN);
		
		ArrayList<String> ibans = internet.getIBANs();
		assertEquals(1, ibans.size());
		assertTrue(ibans.contains(ADDED_IBAN));
	}
	
	@Test
    public void resetClearsCurrentState() throws Exception {		
		internet.publishIBAN(ADDED_IBAN);
		
		ArrayList<String> oneIban = internet.getIBANs();
		assertEquals(1, oneIban.size());
		
		internet.reset();
		
		ArrayList<String> noIbans = internet.getIBANs();
		assertEquals(0, noIbans.size());
	}
	
	@Test
    public void addBank() throws Exception {
		HashMap<String, Bank> banks;
		int endBankCount = 5;
		TaxBureau taxBureau = new TaxBureau();
		
		banks = internet.getBanks();
		assertEquals(0, banks.size());
		
		internet.publishBank(taxBureau.createBank());
		banks = internet.getBanks();
		assertEquals(1, banks.size());		
	}
}
