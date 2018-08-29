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

	public BookKeeping registerBankBookKeeping() {
		BookKeeping bookKeeping = new BookKeeping(++bankCount);
		businesses.add(bookKeeping);
		return bookKeeping;
	}
	
	@Override
	public FinancialReport aggregate() {
		FinancialReport report = new FinancialReport();
		report.setSalaryTotal(getTotalSalary());
		report.setCashTotal(getTotalCash());
		report.setDepositTotal(getTotalDeposits());
		return report;
	}

	private long getTotalDeposits() {
		return businesses.stream()
				.map(business -> business.getAccountTotal())
				.mapToInt(Integer::intValue).sum();
	}
	
	private long getTotalSalary() {
		return people.stream()
				.map(person -> person.getIncomeTotal())
				.mapToInt(Integer::intValue).sum();
	}
	
	private long getTotalCash() {
		return people.stream()
				.map(person -> person.getCash())
				.mapToInt(Integer::intValue).sum();
	}

}
