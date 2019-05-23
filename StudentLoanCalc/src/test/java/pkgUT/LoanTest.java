package pkgUT;

import static org.junit.Assert.*;

import java.time.LocalDate;

import app.Helper.Loan;
import app.Helper.Payment;

import org.junit.Test;

public class LoanTest {

	public static LocalDate ld = LocalDate.of(2019,5,1);
	
	@Test
	public void test() {
		Loan L = new Loan(1000.0, 0.07, 1, 10.0,ld);
		for(Payment p : L.getLoanPayments()) {
			System.out.println(p.getBalance());
		}
		System.out.println("Last Principle Payment: " + L.getLoanPayments().getLast().getPrinciple());
		System.out.println("Last Interest Payment: " + L.getLoanPayments().getLast().getInterest());

		
		//fail("Not yet implemented");
	}

	@Test
	public void test1() {
		//int i = 1;
		double sum = 0.0;
		Loan studentL = new Loan(80000.0, .07, 15, 100,ld);
		for(Payment p : studentL.getLoanPayments()) {
			//System.out.println(i+" month interest: " + p.getInterest());
			//System.out.println();
			sum+=p.getInterest();
			//i++;
			
		}
		System.out.println("Total Interest: " + sum);
		System.out.println("Calc Total Interest: " + studentL.calcInterestPayment());
	}
	
	@Test
	public void test2() {
		//int i = 1;
		double sum = 0.0;
		Loan studentL = new Loan(150000.0,.07,20,200,ld);
		for (Payment p : studentL.getLoanPayments()) {
			//System.out.println(i + " month interest: " + p.getInterest());
			//System.out.println();
			sum+=p.getInterest();
			//i++;
		}
		System.out.println("sum is: " + sum);
		System.out.println("calc sum is: " + studentL.calcInterestPayment());
	}

	//These following tests test the methods in Payment and Loan
	@Test
	public void totalPrincipleTest() {
		Loan L = new Loan(1000.0, 0.07, 1, 10.0,ld);
		double princ = L.calcPrinciplePayment();
		System.out.println("Total Principal Payments: " + princ);
		assertTrue(princ == 1000.0);
	}
	
	@Test
	public void totalInterestTest() {
		Loan L = new Loan(1000.0, 0.07, 1, 10.0,ld);
		double interest = L.calcInterestPayment();
		System.out.println("Total Interest Payments: " + interest);
		assertTrue(interest == 34.54);
	}
	
	@Test
	public void totalPrincipleTest1() {
		System.out.println("80,000 loan");
		Loan L = new Loan(80000.0, .07, 15, 100.0,ld);
		double prin = L.calcPrinciplePayment();
		assertTrue(prin == 80000.0);
		
	}
	
	@Test
	public void totalInterestTest1() {
		System.out.println("150,000 loan");
		Loan L = new Loan(150000.0, .07, 20,200.0,ld);
		double inte = L.calcInterestPayment();
		System.out.println("Total interest Payments is: " + inte);
		assertTrue(inte ==90702.59 );
	}
	
}
