package com.anzisolutions.bankingsimulator;

import java.util.ArrayList;
import java.util.UUID;

import com.anzisolutions.bankingsimulator.clientbase.ClientFinances;

public class TaxBureau {
	private static int bankCount = 0;
	
	public static int getNextBankID() {
		return ++bankCount;
	}
	
	private ArrayList<ClientFinances> people = new ArrayList<ClientFinances>();

	public ClientFinances registerClient() {
		String taxID = UUID.randomUUID().toString();
		ClientFinances clientFinances = new ClientFinances(taxID);
		people.add(clientFinances);
		return clientFinances;
	}

	public int getTotalSalaryFund() {
		return people.stream()
				.map(person -> person.getIncomeTotal())
				.mapToInt(Integer::intValue).sum();
	}

}
