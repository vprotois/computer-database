package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

import model.builders.ComputerBuilder;

public class ComputerBuilderTest {

	@Test
	public void testBuild() {
		Computer c1 = new Computer(1L, "name",null,null,null,1L);
		Computer c2 = new ComputerBuilder()
				.withId(1L)
				.withName("name")
				.withCompanyId(1L)
				.build();
		assertEquals(c1,c2);
		assertEquals(new Long(1L),c2.getId());
		assertEquals("name",c2.getName());
		assertEquals(new Long(1L),c2.getCompanyId());
		assertNull(c2.getIntroduced());
		assertNull(c2.getDiscontinued());
		assertNull(c2.getCompany());
	}
	
	@Test
	public void testBuildTimestamp() {
		Timestamp t1 = new Timestamp(1000000);
		Timestamp t2 = new Timestamp(2000000);
		Computer c1 = new Computer(1L, "ane",null,t1,t2,null);
		Computer c2 = new ComputerBuilder()
				.withId(1L)
				.withName("ane")
				.withIntroduced(t1)
				.withDiscontinued(t2)
				.build();
		assertEquals(c1,c2);
		assertEquals(c2.getIntroduced(),t1);
		assertEquals(c2.getDiscontinued(),t2);
		assertNull(c2.getCompany());
		assertNull(c2.getCompanyId());
	}
	
	@Test
	public void testBuildWrongTimestamp() {
		Timestamp t1 = new Timestamp(2000000);
		Timestamp t2 = new Timestamp(100000);
		Computer c1 = new Computer(1L, "ane",null,t1,t2,null);
		Computer c2 = new ComputerBuilder()
				.withId(1L)
				.withName("ane")
				.withIntroduced(t1)
				.withDiscontinued(t2)
				.build();
		assertNotEquals(c1,c2);
		assertEquals(c2.getIntroduced(),t1);
		assertNull(c2.getDiscontinued());
		assertNull(c2.getCompany());
		assertNull(c2.getCompanyId());
	}
	
	
	
	

}
