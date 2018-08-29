package com.anzisolutions.bankingsimulator;

public class FinancialReport {
	private static final String LINE_TEMPLATE = "%-14.14s : %.2f \n";

	private long salaryTotal;
	private long cashTotal;
	private long depositTotal;
	
	public long getSalaryTotal() {		
		return salaryTotal;
	}
	
	public long getCashTotal() {		
		return cashTotal;
	}
	
	public long getDepositTotal() {
		return depositTotal;
	}
	
	public void setSalaryTotal(long salaryTotal) {
		this.salaryTotal = salaryTotal;
	}
	
	public void setCashTotal(long cashTotal) {
		this.cashTotal = cashTotal;
	}
	
	public void setDepositTotal(long depositTotal) {
		this.depositTotal = depositTotal;
	}
	
	@Override
	public String toString() {
		String answer = "";
		answer += String.format(LINE_TEMPLATE, "Salary total", salaryTotal/100.0);
		answer += String.format(LINE_TEMPLATE, "Cash total", cashTotal/100.0);
		answer += String.format(LINE_TEMPLATE, "Deposit total", depositTotal/100.0);
		long diff = salaryTotal - cashTotal - depositTotal;
		answer += String.format(LINE_TEMPLATE, "Diff", diff/100.0);
		return answer;
	}
}
