package mapper;

import model.Computer;
import model.dto.DTOComputer;

public class DTOComputerMapper {

	
	
	public static DTOComputer mapComputerToDTO(Computer c) {
		DTOComputer dto = new DTOComputer();
		dto.id = c.getId().longValue();
		dto.name = c.getName();
		if(c.getIntroduced() == null) {
			dto.introduced = "";			
		}else {
			dto.introduced = c.getIntroduced().toString();
		}
		if(c.getDiscontinued() == null) {
			dto.discontinued = "";			
		}else {
			dto.discontinued = c.getDiscontinued().toString();
		}
		dto.companyId = c.getCompanyId().longValue();
		
		return dto;
	}
	
}
