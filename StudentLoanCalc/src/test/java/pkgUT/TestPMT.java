package pkgUT;

import static org.junit.Assert.*;
import org.apache.poi.ss.formula.functions.*;
import org.junit.Test;

import org.junit.Test;

public class TestPMT {

	@Test
	public void test() {
		double r = 0.07/12;
		double PMT;
		double n = 20*12;
		double p = 150000;
		double f = 0;
		boolean t = false;
		PMT = FinanceLib.pmt(r, n, p, f, t);
		
		double PMTExpected = 1162.95;
		System.out.println(PMT);
		assertEquals(PMTExpected, PMT,0.01);
		
	}

}
