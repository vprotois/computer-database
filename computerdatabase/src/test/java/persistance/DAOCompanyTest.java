package persistance;

import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.jupiter.api.Test;

import model.Company;


public class DAOCompanyTest extends DBTestCase{

	private static String path_data = "/home/excilys/eclipse-workspace/computer-database/computerdatabase/src/test/java/persistance/test_data.xml";
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream(path_data));
	}
	
	
	@Test
	public void getCompanies() {
		DAOCompany dao = DAOFactory.createDAOcompany();
		Optional<List<Company>> optList = dao.getCompanies();
		assertTrue(optList.isPresent());
		List<Company>list =optList.get();
		assertEquals(new Integer(42),new Integer(list.size()));
		list.forEach( c -> companyValid(c) );
	}

	private void companyValid(Company c) {
		assertNotNull(c);
		assertNotNull(c.getId());
		assertNotNull(c.getName());
	}
	
	@Test
	public void testGetCompany() {
		DAOCompany dao = DAOFactory.createDAOcompany();
		Optional<Company> optc = dao.getCompany(24L);
		assertTrue(optc.isPresent());
		Company c = optc.get();
		companyValid(c);
		assertEquals(c.getId(),new Long(24L));
		assertEquals(c.getName(),"Nintendo");
	}
	
	@Test
	public void testGetWrongCompany() {
		DAOCompany dao = DAOFactory.createDAOcompany();
		Optional<Company> c = dao.getCompany(-124L);
		assertFalse(c.isPresent());
	}
	

	
}
