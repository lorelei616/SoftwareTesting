package stExperiment1;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class TestBill {
	Bill bill = null;
	@Before
	public void setUp() {   //setUp用于生成前缀值
		bill = new Bill();
	}
	
	@Test
	public void testAdd(){
		
		assertEquals(false,bill.accumulated(79));
		assertEquals(true,bill.accumulated(78));
		assertEquals(true,bill.accumulated(5));
	}
}
