package com.anzisolutions.bankingsimulator.client;

import java.util.Random;

import com.anzisolutions.bankingsimulator.TaxBureau;
import com.anzisolutions.bankingsimulator.client.decision.Brain;

public class Population {

	private TaxBureau taxBureau;
	
	public Population(TaxBureau taxBureau) {
		this.taxBureau = taxBureau;
	}

	public Client createClient() {
		Client client = new Client(new Brain(new Random()));
		client.setFinances(taxBureau.registerClient());
		
		return client;
	}

}
