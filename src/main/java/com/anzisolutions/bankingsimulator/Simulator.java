package com.anzisolutions.bankingsimulator;

import com.anzisolutions.bankingsimulator.bankingsystem.BankingSystem;
import com.anzisolutions.bankingsimulator.client.ClientBase;
import com.anzisolutions.bankingsimulator.client.Population;
import com.anzisolutions.bankingsimulator.thread.Controller;
import com.anzisolutions.bankingsimulator.thread.KillSwitch;
import com.anzisolutions.bankingsimulator.thread.KillSwitchImpl;

public class Simulator {
	public static void main(String[] args) {
		TaxBureau taxBureau = new TaxBureau();
		Internet internet = InternetImpl.getInsatnce();
		KillSwitch killSwitch = new KillSwitchImpl();
		
		Controller controller = new Controller(killSwitch);
		
		
		Population population = new Population(taxBureau, internet);
		ClientBase clientBase = new ClientBase(population, controller);
	
		BankingSystem bankSystem = new BankingSystem(taxBureau, internet);
		
		bankSystem.start(1);
		clientBase.start(1);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		controller.finish();
		
	}
}
