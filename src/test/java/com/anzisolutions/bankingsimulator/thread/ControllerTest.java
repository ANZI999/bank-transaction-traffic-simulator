package com.anzisolutions.bankingsimulator.thread;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ControllerTest {
	
	@Mock
	private KillSwitch killSwitch;
	
	@Mock
	private Aggregator aggreagtor;
	
	@InjectMocks
	private Controller controller;
	
	@Test
	public void finish() throws Exception {
		controller.setAggregator(aggreagtor);
		controller.finish();
		verify(killSwitch, times(1)).activate();
		verify(aggreagtor, times(1)).aggregate();
	}
}
