package com.anzisolutions.bankingsimulator.client;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.TaxBureau;

@RunWith(SpringRunner.class)
public class PopulationTest {
	
	@Mock
	private TaxBureau taxBureau;
	
	@Mock
	private Internet internet;

	@Test
    public void createClient() throws Exception {
		Population population = new Population(taxBureau);
		Client client = population.createClient();
		
		assertThat(client, instanceOf(Client.class));
		verify(taxBureau, times(1)).registerClient();
	}

}
