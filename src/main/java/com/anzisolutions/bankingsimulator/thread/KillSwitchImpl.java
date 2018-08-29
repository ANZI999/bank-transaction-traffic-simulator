package com.anzisolutions.bankingsimulator.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class KillSwitchImpl implements KillSwitch {
	
	private AtomicInteger threadCount = new AtomicInteger();
	private boolean allowRegistering = true;
	private boolean isActivated = false;
	private CountDownLatch countDownLatch;

	@Override
	public boolean isActivated() {
		boolean answer = isActivated;
		if(answer) {
			countDownLatch.countDown();
		}
		
		return answer;
	}

	@Override
	public void activate() {	
		allowRegistering = false;
		
		countDownLatch = new CountDownLatch(threadCount.get());
		isActivated = true;
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean registerThread() {
		if(allowRegistering) {
			threadCount.incrementAndGet();
			return true;
		} else {
			return false;
		}
	}
}
