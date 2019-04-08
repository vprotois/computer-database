package validator;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import exception.ValidatorException;
import model.Company;
import model.Computer;
import services.CompanyServices;

public class Validator {
	
	public static final String ERROR_COMPUTER_COMPANIES = "Can't find companies in base";
	public static final String ERROR_COMPANY_NOT_FOUND = "Company doesn't exist in base";
	public static final String ERROR_COMPANY_INVALID_NAME = "Company must have a name";
	public static final String ERROR_COMPANY_INVALID_ID = "Company must have an id";
	
	public static final String ERROR_COMPUTER_NAME = "Computer must have a name";
	public static final String ERROR_COMPUTER_DISC_WITHOUT_INTR = "Can't have discontinued without introduced";
	public static final String ERROR_COMPUTER_DISC_INFERIOR = "Can't have discontinued inferior than introduced";
	
	
	
	public static void computerValidator(Computer computer) throws ValidatorException {
		nameValid(computer);
		intrAndDiscValid(computer);
		companyValid(computer);
	}


	private static void companyValid(Computer computer) throws ValidatorException {
		if(computer.getCompany() != null) {
			companyValidator(computer.getCompany());
		}
	}

	private static void intrAndDiscValid(Computer computer) throws ValidatorException {
		Timestamp intr = computer.getIntroduced();
		Timestamp disc = computer.getDiscontinued();
		
		if(intr== null && disc != null) {
			throw new ValidatorException(ERROR_COMPUTER_DISC_WITHOUT_INTR);
		}
		
		if(intr != null && disc != null && intr.compareTo(disc)>0) {
			throw new ValidatorException(ERROR_COMPUTER_DISC_INFERIOR);
		}
	}


	private static void nameValid(Computer computer) throws ValidatorException {
		if (computer == null || computer.getName() == null || "".equals(computer.getName())) {
			throw new ValidatorException(ERROR_COMPUTER_NAME);
		}
	}
	
	
	public static void companyValidator(Company company) throws ValidatorException {
		CompanyServices companyServices = new CompanyServices();
		Optional<List<Company>> optList = companyServices.listCompanies();
		if(!optList.isPresent()) {
			throw new ValidatorException(ERROR_COMPUTER_COMPANIES);
		}
		List<Company> list = optList.get();
		if(!list.contains(company)) {
			throw new ValidatorException(ERROR_COMPANY_NOT_FOUND);
		}
		
		if(company.getId() == null) {
			throw new ValidatorException(ERROR_COMPANY_INVALID_ID);
		}
		
		if(company.getName() == null) {
			throw new ValidatorException(ERROR_COMPANY_INVALID_NAME);
		}
		
	}

}
