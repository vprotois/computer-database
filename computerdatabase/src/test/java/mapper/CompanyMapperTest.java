package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import junit.framework.TestCase;
import model.Company;

class CompanyMapperTest  extends TestCase{

	@Mock
	ResultSet rs = Mockito.mock(ResultSet.class);
	
	@BeforeEach
	public void setUp() throws SQLException {
		mockitoSetUpId();
		mockitoSetUpName();
		mockitoSetUpNext();
	}
	
	private void mockitoSetUpNext() throws SQLException {
		Mockito.when(rs.next())
			.thenReturn(true)
			.thenReturn(true)
			.thenReturn(false);
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
	void testSingleCompany() throws SQLException {
		Optional<Company> optc = CompanyMapper.mapSingleCompany(rs);
		assertTrue(optc.isPresent());
		Company c = optc.get();
		assertEquals(new Long(1),c.getId());
		assertEquals("azaz1234",c.getName());
	}
	
	@Test
	void testListComputer() throws SQLException {
		List<Company> list = new ArrayList<Company>();
		list.add(new Company(1L,"azaz1234"));
		list.add(new Company(4L,"QWERT"));
		List<Company> result = CompanyMapper.mapCompaniesList(rs);
		assertEquals(list,result);
	}
}
