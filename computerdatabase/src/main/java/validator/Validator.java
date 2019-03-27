package validator;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import exception.ValidatorException;
import model.Company;
import model.Computer;
import services.CompanyServices;

public class Validator {
	
	public static final String ERROR_COMPUTER_BAD_COMPANY = "Company doesn't exist in base";
	public static final String ERROR_COMPUTER_COMPANIES = "Can't find companies in base";
	public static final String ERROR_COMPUTER_NAME = "Computer must have a name";
	public static final String ERROR_COMPUTER_DISC_WITHOUT_INTR = "Can't have discontinued without introduced";
	public static final String ERROR_COMPUTER_DICS_INFERIOR = "Can't have discontinued inferior than introduced";
	
	public static void computerValidator(Computer computer) throws ValidatorException {
		Timestamp intr = computer.getIntroduced();
		Timestamp disc = computer.getDiscontinued();
		if (computer == null || computer.getName() == null && !"".equals(computer.getName())) {
			throw new ValidatorException(ERROR_COMPUTER_NAME);
		}
		if(intr== null && disc != null) {
			throw new ValidatorException(ERROR_COMPUTER_DISC_WITHOUT_INTR);
		}
		if(intr != null && disc != null && intr.compareTo(disc)>0) {
			throw new ValidatorException(ERROR_COMPUTER_DICS_INFERIOR);
		}
		
		if(computer.getCompany() != null) {
			CompanyServices companyServices = new CompanyServices();
			Optional<List<Company>> optList = companyServices.listCompanies();
			if(!optList.isPresent()) {
				throw new ValidatorException(ERROR_COMPUTER_COMPANIES);
			}
			List<Company> list = optList.get();
			if(!list.contains(computer.getCompany())) {
				throw new ValidatorException(ERROR_COMPUTER_BAD_COMPANY);
			}
		}
	}
	
	
	public static boolean companyValidator(Company company) {
		return company.getId() !=null && company.getName() != null;
	}

}
