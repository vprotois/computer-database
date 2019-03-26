package mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class TimeStampMapperTest {

	@Test
	void simpleStringToTimestampNullTest() {
		Timestamp t1 = TimeStampMapper.simpleStringToTimestamp("");
		assertNull(t1);
		t1 = TimeStampMapper.simpleStringToTimestamp(null);
		assertNull(t1);
		t1 = TimeStampMapper.simpleStringToTimestamp("null");
		assertNull(t1);
	}

	@Test
	void simpleStringToTimestampErrorTest() {
		Timestamp t1 = TimeStampMapper.simpleStringToTimestamp("12/123/12");
		assertNull(t1);
		
	}
	
	@Test
	void simpleStringToTimestampTest() {
		Timestamp t1 = TimeStampMapper.simpleStringToTimestamp("12/12/1999");
		assertEquals("1999-12-12 00:00:00.0",t1.toString());
	}
}
