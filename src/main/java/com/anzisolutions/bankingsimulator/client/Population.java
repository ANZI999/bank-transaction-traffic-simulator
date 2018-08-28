package com.anzisolutions.bankingsimulator.client;

import java.util.Random;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.TaxBureau;
import com.anzisolutions.bankingsimulator.client.decision.Brain;

public class Population {

	private TaxBureau taxBureau;
	private Internet internet;
	
	public Population(TaxBureau taxBureau, Internet internet) {
		this.taxBureau = taxBureau;
		this.internet = internet;
	}

	public Client createClient() {
		Client client = new Client(new Brain(new Random()));
		client.setFinances(taxBureau.registerClient());
		client.setInternet(internet);
		return client;
	}

}
