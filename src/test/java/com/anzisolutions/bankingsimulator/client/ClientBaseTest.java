package com.anzisolutions.bankingsimulator.client;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.thread.Controller;
import com.anzisolutions.bankingsimulator.thread.KillSwitch;
import com.anzisolutions.bankingsimulator.thread.Worker;

@RunWith(SpringRunner.class)
public class ClientBaseTest {
	
	@Mock
	private Population population;
	
	@Mock
	private Controller controller;
	
	@InjectMocks
	private ClientBase clientBase;
	
	@Test
	public void start() throws Exception {
		Worker worker = mock(Worker.class);
		Client client = mock(Client.class);
		int clientCount = 10;
		when(population.createClient()).thenReturn(client);
		when(controller.getWorker()).thenReturn(worker);
		clientBase.start(clientCount);
		
		verify(worker, times(clientCount)).setTaskFactory(client);
		verify(worker, times(clientCount)).start();		
	}
}
