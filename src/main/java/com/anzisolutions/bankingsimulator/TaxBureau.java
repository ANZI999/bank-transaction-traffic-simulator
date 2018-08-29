package com.anzisolutions.bankingsimulator;

import java.util.ArrayList;
import java.util.UUID;

import com.anzisolutions.bankingsimulator.bankingsystem.BookKeeping;
import com.anzisolutions.bankingsimulator.client.Finances;
import com.anzisolutions.bankingsimulator.thread.Aggregator;

public class TaxBureau implements Aggregator {
	private static int bankCount = 0;
	
	private ArrayList<Finances> people = new ArrayList<Finances>();
	private ArrayList<BookKeeping> businesses = new ArrayList<BookKeeping>();

	public Finances registerClient() {
		String taxID = UUID.randomUUID().toString();
		Finances clientFinances = new Finances(taxID);
		people.add(clientFinances);
		return clientFinances;
	}

	public int getTotalSalary() {
		return people.stream()
				.map(person -> person.getIncomeTotal())
				.mapToInt(Integer::intValue).sum();
	}

	public BookKeeping registerBankBookKeeping() {
		BookKeeping bookKeeping = new BookKeeping(++bankCount);
		businesses.add(bookKeeping);
		return bookKeeping;
	}

	public int getTotalDeposits() {
		return businesses.stream()
				.map(business -> business.getAccountTotal())
				.mapToInt(Integer::intValue).sum();
	}

	@Override
	public FinancialReport aggregate() {
		FinancialReport report = new FinancialReport();
		report.setSalaryTotal(getTotalSalary());
		return report;
	}

}
