package com.anzisolutions.bankingsimulator.bankingsystem;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class WorkingInternetTest {
	
	final private static String ADDED_IBAN = "dfsdfsdffdsf";
	
	@Before 
	public void setup() {
		WorkingInternet.getInsatnce().reset();
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
		Internet internet = WorkingInternet.getInsatnce();
		
		ArrayList<String> noibans = internet.getIBANs();
		assertEquals(0, noibans.size());
		
		internet.publishIBAN(ADDED_IBAN);
		
		ArrayList<String> ibans = internet.getIBANs();
		assertEquals(1, ibans.size());
		assertTrue(ibans.contains(ADDED_IBAN));
	}
	
	@Test
    public void resetClearsCurrentState() throws Exception {
		WorkingInternet internet = WorkingInternet.getInsatnce();
		
		internet.publishIBAN(ADDED_IBAN);
		
		ArrayList<String> oneIban = internet.getIBANs();
		assertEquals(1, oneIban.size());
		
		internet.reset();
		
		ArrayList<String> noIbans = internet.getIBANs();
		assertEquals(0, noIbans.size());
	}
}
