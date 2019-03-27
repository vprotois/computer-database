package validator;

import java.sql.Timestamp;

import model.Company;
import model.Computer;

public class Validator {
	
	
	public static boolean computerValidator(Computer computer) {
		Timestamp intr = computer.getIntroduced();
		Timestamp disc = computer.getDiscontinued();
		boolean discIsValid = true;
		boolean nameIsValid = computer != null 
								&& computer.getName() != null 
								&& !"".equals(computer.getName());
		if(intr== null && disc != null) {
			discIsValid = false;
		}
		if(intr != null && disc != null) {
			discIsValid = intr.compareTo(disc)<0;
		}
		return nameIsValid && discIsValid;
	}
	
	public static boolean companyValidator(Company company) {
		return company.getId() !=null && company.getName() != null;
	}

}
