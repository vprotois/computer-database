package mapper;

import model.Computer;
import model.dto.DTOComputer;

public class DTOComputerMapper {

	private static final String EMPTY = "";
	
	public static DTOComputer mapComputerToDTO(Computer c) {
		DTOComputer dto = new DTOComputer();
		dto.setId(c.getId().longValue());
		dto.setName( c.getName());
		if(c.getIntroduced() == null) {
			dto.setIntroduced(EMPTY);			
		}else {
			dto.setIntroduced(c.getIntroduced().toString());
		}
		if(c.getDiscontinued() == null) {
			dto.setDiscontinued(EMPTY);			
		}else {
			dto.setDiscontinued(c.getDiscontinued().toString());
		}
		dto.setCompany( c.getCompany().getName());
		
		return dto;
	}
	
}
