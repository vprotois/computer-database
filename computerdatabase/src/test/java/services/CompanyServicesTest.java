package services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CompanyServicesTest {

	@Test
	void deleteTest() {
		CompanyServices companyServ = new CompanyServices();
		companyServ.deleteCompany(1L);
		assertFalse(companyServ.getCompany(1L).isPresent());
	}

}
