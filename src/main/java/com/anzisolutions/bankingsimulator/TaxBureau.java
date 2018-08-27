package com.anzisolutions.bankingsimulator;

import java.util.ArrayList;
import java.util.UUID;

import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.BookKeeping;
import com.anzisolutions.bankingsimulator.client.Finances;

public class TaxBureau {
	private static int bankCount = 0;
	
	public static int getNextBankID() {
		return ++bankCount;
	}
	
	private ArrayList<Finances> people = new ArrayList<Finances>();

	public Finances registerClient() {
		String taxID = UUID.randomUUID().toString();
		Finances clientFinances = new Finances(taxID);
		people.add(clientFinances);
		return clientFinances;
	}

	public int getTotalSalaryFund() {
		return people.stream()
				.map(person -> person.getIncomeTotal())
				.mapToInt(Integer::intValue).sum();
	}

	public BookKeeping registerBankBookKeeping() {
		return new BookKeeping(++bankCount);
	}

}
