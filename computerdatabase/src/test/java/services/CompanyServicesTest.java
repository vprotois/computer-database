package services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import app.AppConfig;

class CompanyServicesTest {

	private CompanyServices companyServ;
	GenericApplicationContext context;
	
	@BeforeAll
	public void setUp() {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		companyServ = context.getBean(CompanyServices.class);
	}
	
	@Test
	void deleteTest() {
		companyServ.deleteCompany(1L);
		assertFalse(companyServ.getCompany(1L).isPresent());
	}

}
