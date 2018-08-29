package com.anzisolutions.bankingsimulator.client;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.thread.KillSwitch;

@RunWith(SpringRunner.class)
public class ClientBaseTest {
	
	@Mock
	private Population population;
	
	@Mock
	private KillSwitch killSwitch;
	
	@Mock
	private Client client;
	
	@InjectMocks
	private ClientBase clientBase;
	
	@Test
	public void start() throws Exception {
		int clientCount = 10;
		when(population.createClient()).thenReturn(client);
		clientBase.start(clientCount);
		
		verify(population, times(clientCount)).createClient();
		verify(client, times(clientCount)).setKillSwitch(any(KillSwitch.class));
		verify(client, times(clientCount)).start();
	}
}
