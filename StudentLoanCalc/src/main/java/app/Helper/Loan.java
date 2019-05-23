package app.Helper;

import java.time.LocalDate;
import java.util.LinkedList;
import org.apache.poi.ss.formula.functions.*;

public class Loan {
	double dLoanAmount;
	double dInterestRate;
	int iTerm;
	double dFutureValue;
	boolean bInterestCalc;
	double dExtraPayment;
	double monthlyPayment;
	LocalDate pmtDate;
	
	private LinkedList<Payment> loanPayments = new LinkedList<Payment>();
	
	public Loan(double loanAmount, double interest,
			int term, double extraPayment, LocalDate ld) {
		//super();
		dLoanAmount = loanAmount;
		dInterestRate = interest;
		iTerm = term;
		dExtraPayment = extraPayment;
		dFutureValue = 0.0;
		pmtDate= ld;
		calcPayment();
		goLoan();
	}
	
	private void calcPayment() {
	//System.out.println(dLoanAmount+ ", "+ dInterestRate+", " +iTerm + ", "+dExtraPayment);
		monthlyPayment = Math.round(-1*FinanceLib.pmt(dInterestRate/12, 12*iTerm, dLoanAmount, 0, false)*100.0)/100.0;
	//System.out.println("Monthly Payment is: " + monthlyPayment);
	}
	
	private void goLoan() {
		
		do {
			Payment p = new Payment(dLoanAmount,dInterestRate,monthlyPayment,dExtraPayment,pmtDate);
			dLoanAmount = p.getBalance();
			loanPayments.add(p);
			pmtDate = pmtDate.plusMonths(1);
			
			
		}
		while(dLoanAmount > (monthlyPayment+dExtraPayment));
		Payment p = new Payment(dLoanAmount, dInterestRate, monthlyPayment, dExtraPayment,pmtDate.plusMonths(1));
		loanPayments.add(p);
		p.setPmtNbr(0);
	}
	
	public double calcInterestPayment() {
		double interestSum = 0.0;
		for (Payment p : this.getLoanPayments()) {
			interestSum +=p.getInterest();
			
		}
		return Math.round(interestSum*100.0)/100.0;
	}
	
	public double calcPrinciplePayment() {
		double principleSum = 0.0;
		for (Payment p : this.getLoanPayments()) {
			principleSum += p.getPrinciple();
		}
		return Math.round(principleSum*100.0)/100.0;
	}
	
	public double calcTotalPayment() {
		return calcPrinciplePayment() + calcInterestPayment();
	}
	
	public LinkedList<Payment> getLoanPayments(){
		return loanPayments;
	}
	
}
