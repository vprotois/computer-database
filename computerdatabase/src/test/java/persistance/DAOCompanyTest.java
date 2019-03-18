package persistance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import model.Company;
import model.Pages;


public class DAOCompanyTest{

	@Test
	public void getCompanies() {
		DAOCompany dao = DAOFactory.createDAOcompany();
		List<Company> list = dao.getCompanies();
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
		Company c = dao.getCompany(24L);
		companyValid(c);
		assertEquals(c.getId(),new Long(24L));
		assertEquals(c.getName(),"Nintendo");
	}
	
	@Test
	public void testGetWrongCompany() {
		DAOCompany dao = DAOFactory.createDAOcompany();
		Company c = dao.getCompany(-124L);
		assertNull(c);
	}
	

	@Test 
	public void pageListCompany(){
		DAOCompany dao = DAOFactory.createDAOcompany();
		Pages<Company> page = dao.pageListCompany();
		assertNotNull(page);
	}


	
}
