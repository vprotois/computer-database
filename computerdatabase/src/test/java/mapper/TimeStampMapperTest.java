package mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class TimeStampMapperTest {

	@Test
	void simpleStringToTimestampNullTest() {
		Optional<Timestamp> t1 = TimeStampMapper.simpleStringToTimestamp("");
		assertFalse(t1.isPresent());
		t1 = TimeStampMapper.simpleStringToTimestamp(null);
		assertFalse(t1.isPresent());
		t1 = TimeStampMapper.simpleStringToTimestamp("null");
		assertFalse(t1.isPresent());
	}

	@Test
	void simpleStringToTimestampErrorTest() {
		Optional<Timestamp> t1 = TimeStampMapper.simpleStringToTimestamp("12/123/12");
		assertFalse(t1.isPresent());
		
	}
	
	@Test
	void simpleStringToTimestampTest() {
		Optional<Timestamp> t1 = TimeStampMapper.simpleStringToTimestamp("12/12/1999");
		assertTrue(t1.isPresent());
		assertEquals("1999-12-12 00:00:00.0",t1.get().toString());
	}
}
