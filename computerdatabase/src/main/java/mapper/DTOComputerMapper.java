package mapper;

import model.Computer;
import model.dto.DTOComputer;

public class DTOComputerMapper {

	private static final String EMPTY = " ";
	
	public static DTOComputer mapComputerToDTO(Computer computer) {
		DTOComputer dto = new DTOComputer();
		dto.setId(computer.getId().longValue());
		dto.setName( computer.getName());
		dto.setIntroduced(computer.getIntroduced() != null? 
				computer.getIntroduced().toString() : EMPTY);
		dto.setDiscontinued(computer.getDiscontinued() != null? 
				computer.getDiscontinued().toString() : EMPTY);
		dto.setCompany( computer.getCompany() != null ? computer.getCompany().getName() : EMPTY);
		return dto;
	}
	
}
