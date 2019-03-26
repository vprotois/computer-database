package mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import model.Computer;


class ComputerMapperTest {

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
		Mockito.when(rs.getLong("company_id"))
				.thenReturn(new Long(5))
				.thenReturn(new Long(5));
	}

	private void mockitoSetUpDiscontinued() throws SQLException {
		Mockito.when(rs.getTimestamp("discontinued"))
				.thenReturn(new Timestamp(4000))
				.thenReturn(new Timestamp(64564));
	}

	private void mockitoSetUpIntroduced() throws SQLException {
		Mockito.when(rs.getTimestamp("introduced"))
				.thenReturn(new Timestamp(1000))
				.thenReturn(new Timestamp(4564));
	}

	private void mockitoSetUpName() throws SQLException {
		Mockito.when(rs.getString("name"))
				.thenReturn("azaz1234")
				.thenReturn("QWERT");
	}

	private void mockitoSetUpId() throws SQLException {
		Mockito.when(rs.getLong("id"))
				.thenReturn(new Long(1))
				.thenReturn(new Long(4));
	}
	
	@Test
	void testSingleComputer() throws SQLException {
		Optional<Computer> optComputer = ComputerMapper.mapSingleComputer(rs);
		assertTrue(optComputer.isPresent());
		Computer computer = optComputer.get();
		assertEquals(new Long(1),computer.getId());
		assertEquals("azaz1234",computer.getName());
		assertEquals(new Timestamp(1000),computer.getIntroduced());
		assertEquals(new Timestamp(4000),computer.getDiscontinued());
		assertEquals(new Long(5),computer.getCompanyId());
	}

	@Test
	void testListComputer() throws SQLException {
		List<Computer> list = new ArrayList<Computer>();
		list.add(new Computer(1L,"azaz1234",null,new Timestamp(1000),new Timestamp(4000),5L));
		list.add(new Computer(4L,"QWERT",null,new Timestamp(4564),new Timestamp(64564),5L));
		List<Computer> result = ComputerMapper.mapComputerList(rs);
		assertEquals(list,result);
	}
}
