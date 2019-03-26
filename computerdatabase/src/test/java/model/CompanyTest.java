package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompanyTest {

	@Test
	public void testEquals() {
		Company c1 = new Company();
		assertTrue(c1.equals(c1));
		
		Company c2 = new Company(1l,"name");
		assertTrue(c2.equals(c2));
		
		assertFalse(c1.equals(c2));
		
	}

}
