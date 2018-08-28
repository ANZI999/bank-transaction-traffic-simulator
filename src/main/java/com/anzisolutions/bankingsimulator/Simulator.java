package com.anzisolutions.bankingsimulator;

import com.anzisolutions.bankingsimulator.bankingsystem.BankingSystem;
import com.anzisolutions.bankingsimulator.client.ClientBase;
import com.anzisolutions.bankingsimulator.client.EndSimulationImpl;
import com.anzisolutions.bankingsimulator.client.Population;

public class Simulator {
	public static void main(String[] args) {
		TaxBureau taxBureau = new TaxBureau();
		Internet internet = InternetImpl.getInsatnce();
		
		EndSimulationImpl endSimulation = new EndSimulationImpl();
		Population population = new Population(taxBureau, internet);
		ClientBase clientBase = new ClientBase(population, endSimulation);
	
		BankingSystem bankSystem = new BankingSystem(taxBureau, internet);
		
		bankSystem.start(1);
		clientBase.start(1);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endSimulation.turnOn();
		
	}
}
