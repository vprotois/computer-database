package persistance;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import app.AppConfig;
import junit.framework.TestCase;
import model.Company;


public class DAOCompanyTest extends TestCase{

	private DAOCompany  daoCompany;
	GenericApplicationContext context;
	
	@BeforeAll
	public void setUp() {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		daoCompany  = context.getBean(DAOCompany.class);
	}
	
	
	@Test
	public void getCompanies() {
		Optional<List<Company>> optList = daoCompany.getCompanies();
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
		Optional<Company> optc = daoCompany.getCompany(24L);
		assertTrue(optc.isPresent());
		Company c = optc.get();
		companyValid(c);
		assertEquals(c.getId(),new Long(24L));
		assertEquals(c.getName(),"Nintendo");
	}
	
	@Test
	public void testGetWrongCompany() {
		Optional<Company> c = daoCompany.getCompany(-124L);
		assertFalse(c.isPresent());
	}
	

	
}
