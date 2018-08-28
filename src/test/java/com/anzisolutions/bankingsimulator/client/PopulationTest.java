package com.anzisolutions.bankingsimulator.client;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.TaxBureau;

@RunWith(SpringRunner.class)
public class PopulationTest {
	private static final String TAX_ID = "tax-id";
	@Mock
	private TaxBureau taxBureau;
	
	@Mock
	private Internet internet;

	@Test
    public void createClient() throws Exception {
		Finances finances = new Finances(TAX_ID);
		when(taxBureau.registerClient()).thenReturn(finances);
		Population population = new Population(taxBureau, internet);
		Client client = population.createClient();
		assertTrue(client.isInitialized());
		assertThat(client, instanceOf(Client.class));
	}

}
