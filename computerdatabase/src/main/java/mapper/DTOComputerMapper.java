package mapper;

import model.Computer;
import model.dto.DTOComputer;

public class DTOComputerMapper {

	private static final String EMPTY = "";
	
	public static DTOComputer mapComputerToDTO(Computer computer) {
		DTOComputer dto = new DTOComputer();
		dto.setId(computer.getId().longValue());
		dto.setName( computer.getName());
		if(computer.getIntroduced() == null) {
			dto.setIntroduced(EMPTY);			
		}else {
			dto.setIntroduced(computer.getIntroduced().toString());
		}
		if(computer.getDiscontinued() == null) {
			dto.setDiscontinued(EMPTY);			
		}else {
			dto.setDiscontinued(computer.getDiscontinued().toString());
		}
		dto.setCompany( computer.getCompany().getName());
		return dto;
	}
	
}
