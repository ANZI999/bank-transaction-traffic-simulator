package com.anzisolutions.bankingsimulator;

public class FinancialReport {

	private int salaryTotal;
	
	public Object getSalaryTotal() {		
		return salaryTotal;
	}
	
	public void setSalaryTotal(int salaryTotal) {
		this.salaryTotal = salaryTotal;
	}
	
	@Override
	public String toString() {
		return "The total accumulated salary is: "+ salaryTotal +"\n";
	}

}
