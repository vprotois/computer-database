package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import junit.framework.TestCase;
import model.Computer;


class ComputerMapperTest extends TestCase {

	@Mock
	ResultSet rs = Mockito.mock(ResultSet.class);
	
	
	@BeforeEach
	public void setUp() throws SQLException {
		mockitoSetUpId();
		mockitoSetUpName();
		mockitoSetUpIntroduced();
		mockitoSetUpDiscontinued();
		mockitoSetUpCompanyId();
		mockitoSetUpNext();
	}

	private void mockitoSetUpNext() throws SQLException {
		Mockito.when(rs.next())
			.thenReturn(true)
			.thenReturn(true)
			.thenReturn(false);
	}

	private void mockitoSetUpCompanyId() throws SQLException {
		Mockito.when(rs.getLong("cr.company_id"))
				.thenReturn(new Long(5))
				.thenReturn(new Long(5));
	}

	private void mockitoSetUpDiscontinued() throws SQLException {
		Mockito.when(rs.getTimestamp("cr.discontinued"))
				.thenReturn(new Timestamp(4000))
				.thenReturn(new Timestamp(64564));
	}

	private void mockitoSetUpIntroduced() throws SQLException {
		Mockito.when(rs.getTimestamp("cr.introduced"))
				.thenReturn(new Timestamp(1000))
				.thenReturn(new Timestamp(4564));
	}

	private void mockitoSetUpName() throws SQLException {
		Mockito.when(rs.getString("cr.name"))
				.thenReturn("azaz1234")
				.thenReturn("QWERT");
	}

	private void mockitoSetUpId() throws SQLException {
		Mockito.when(rs.getLong("cr.id"))
				.thenReturn(new Long(1))
				.thenReturn(new Long(4));
	}
	
	@Test
	void testSingleComputer() throws SQLException {
		Computer computer = new ComputerMapper().buildFromResult(rs);
		assertEquals(new Long(1),computer.getId());
		assertEquals("azaz1234",computer.getName());
		assertEquals(new Timestamp(1000),computer.getIntroduced());
		assertEquals(new Timestamp(4000),computer.getDiscontinued());
		assertEquals(new Long(5),computer.getCompanyId());
	}

}
