package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompanyBuilderTest {

	@Test
	public void test() {
		
		Company c1 = new Company(1L,"name");
		Company c2 = new CompanyBuilder()
				.withId(1L)
				.withName("name")
				.build();
		assertTrue(c1.equals(c2));
	}
	
	
	@Test
	public void testCompanEmpty() {
		Company c1 = new Company();
		Company c2 = new CompanyBuilder().build();
		assertNull(c2);
		assertFalse(c1.equals(c2));
		
	}
	

}
