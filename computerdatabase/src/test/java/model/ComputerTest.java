package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

public class ComputerTest {

	
	@Test
	public void testEquals() {
		Computer c1 = new Computer();
		assertTrue(c1.equals(c1));
		
		Computer c2 = new Computer(1000L,"name",new Company(1000L,"name"),
				new Timestamp(1000),new Timestamp(2000),289L);
		
		assertTrue(c2.equals(c2));
		
		assertFalse(c1.equals(c2));
	}

}
