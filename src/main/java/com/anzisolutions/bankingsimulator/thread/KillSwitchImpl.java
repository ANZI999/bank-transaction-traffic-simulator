package com.anzisolutions.bankingsimulator.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KillSwitchImpl implements KillSwitch {
	
	final Logger logger = LoggerFactory.getLogger(KillSwitchImpl.class);
	
	private AtomicInteger threadCount = new AtomicInteger();
	private boolean allowRegistering = true;
	private boolean isActivated = false;
	private CountDownLatch countDownLatch;

	@Override
	public boolean isActivated() {
		boolean answer = isActivated;
		if(answer) {
			logger.info("Received kill signal");
			countDownLatch.countDown();
		}
		
		return answer;
	}

	@Override
	public void activate() {	
		logger.info("Sending kill signal");
		allowRegistering = false;
		
		countDownLatch = new CountDownLatch(threadCount.get());
		isActivated = true;
		
		try {
			countDownLatch.await();
			logger.info("All threads killed");
		} catch (InterruptedException e) {
		}
	}

	@Override
	public boolean registerThread() {
		if(allowRegistering) {
			logger.info("Registering");
			threadCount.incrementAndGet();
			return true;
		} else {
			return false;
		}
	}
}
