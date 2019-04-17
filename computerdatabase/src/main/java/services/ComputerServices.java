package services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.ComputerNotFoundException;
import exception.CreateComputerError;
import exception.UpdateComputerError;
import exception.ValidatorException;
import mapper.DTOComputerMapper;
import mapper.TimeStampMapper;
import model.Computer;
import model.Pages;
import model.builders.ComputerBuilder;
import model.builders.PagesBuilder;
import model.dto.DTOComputer;
import persistance.DAOComputer;
import validator.Validator;

@Service
public class ComputerServices {

	private static Logger log= LoggerFactory.getLogger(ComputerServices.class);
	
	@Autowired
	private DAOComputer daoComputer;
	
	public static final String NAME = "name";
	public static final String INTRODUCED = "introduced";
	public static final String DISCONTINUED = "discontinued";
	public static final String COMPANY_ID = "company_id";
	public static final String EMPTY = "";


	public void buildComputerWithId(String[] args) throws CreateComputerError, ValidatorException {
		ComputerBuilder cBuilder = new ComputerBuilder()
				.withId(Optional.of(Long.parseLong(args[0])))
				.withName(Optional.of(args[1]))
				.withIntroduced(TimeStampMapper.stringToTimestamp(args[2]))
				.withDiscontinued(TimeStampMapper.stringToTimestamp(args[3]))
				.withCompanyId(Optional.of(Long.parseLong(args[4])));
		
		Computer computer = cBuilder.build();
		Validator.computerValidator(computer);
		daoComputer.createComputer(computer);
	}
	
	public void buildComputer(String name,String introduced,String discontinued,String companyId) throws CreateComputerError, ValidatorException {
		ComputerBuilder compBuilder = new ComputerBuilder()
				.withName(Optional.of(name))
				.withIntroduced(TimeStampMapper.stringToTimestamp(introduced))
				.withDiscontinued(TimeStampMapper.stringToTimestamp(discontinued))
				.withCompanyId(Optional.of(Long.parseLong(companyId)));
		
		Computer comp = compBuilder.build();
		Validator.computerValidator(comp);
		daoComputer.createComputer(comp);

	}
	
	public void addComputer(Computer comp) throws CreateComputerError, ValidatorException {
		Validator.computerValidator(comp);
		log.debug(""+comp);
		daoComputer.createComputer(comp);
	}

	public Optional<List<DTOComputer>> listDTOComputer(){
		Optional<List<Computer>> list = listComputer();
		if(list.isPresent()) {
			List <DTOComputer> dtolist = list.get() .stream()
					.map( c -> DTOComputerMapper.mapComputerToDTO(c))
					.collect(Collectors.toList());
			return Optional.of(dtolist);
		}
		
		return Optional.empty();
	}
	
	public Optional<Pages<DTOComputer>> pagesDTOComputer(Integer size, Integer index,String order,Boolean asc){
		Optional<List<Computer>> list = daoComputer.listComputers(getOrder(order, asc));
		if(list.isPresent()) {
			List<DTOComputer> listDTO = list.get().stream()
											.map(c -> DTOComputerMapper.mapComputerToDTO(c))
											.collect(Collectors.toList());
											
			Pages<DTOComputer> pages = new PagesBuilder<DTOComputer>()
					.withData(listDTO)
					.withIndex(index)
					.withSize(size)
					.build();
			return Optional.of(pages);		
		}
		return Optional.empty();
	}
	
	public Optional<List<Computer>> listComputer() {
		Optional<List<Computer>> list = daoComputer.listComputers("");
		return list;
	}
	
	public Optional<Pages <Computer>> pagesComputer(Integer size, Integer index) {
		Optional<List<Computer>> list = listComputer();
		if(!list.isPresent()) {
			return Optional.empty();
		}
		else {			
			Pages <Computer> pages = new PagesBuilder<Computer>()
					.withData(list.get())
					.withIndex(index)
					.withSize(size)
					.build();
			return Optional.of(pages);
		}
	}
	
	public Optional<Pages <DTOComputer>> pagesComputerWithName(String name, Integer size, Integer index,String order,Boolean asc) {
		Optional<List<Computer>> list = daoComputer.getListFromName(name,getOrder(order, asc));
		if(list.isPresent()) {
			List<DTOComputer> listDTO =list.get().stream()
					.map(c -> DTOComputerMapper.mapComputerToDTO(c))
					.collect(Collectors.toList());
			
			Pages <DTOComputer> pages = new PagesBuilder<DTOComputer>()
					.withData(listDTO)
					.withIndex(index)
					.withSize(size)
					.build();
			return Optional.of(pages);
		}
		return Optional.empty();
	}

	private String getOrder(String order,Boolean asc){
		switch(order) {
		case EMPTY:
			return "";
		case NAME:
			return asc ? " ORDER BY cr.name ASC" : "ORDER BY cr.name DESC";
		case INTRODUCED:
			return asc ? " ORDER BY cr.introduced ASC" : "ORDER BY cr.introduced DESC";
		case DISCONTINUED:
			return asc ? " ORDER BY cr.discontinued ASC" : "ORDER BY cr.discontinued DESC";
		case COMPANY_ID:
			return asc ? " ORDER BY cy.name ASC" : "ORDER BY cy.name DESC";
		default:
			return "";
		}
		
	}
	
	public DTOComputer getComputerDTO(Long id) throws ComputerNotFoundException {
		
		Optional<Computer> c = daoComputer.getCompDetails(id);
		if(!c.isPresent()) {
			throw new ComputerNotFoundException("Computer not in base");
		}
		return DTOComputerMapper.mapComputerToDTO(c.get());
	}

	public void deleteComputer(Long id) throws UpdateComputerError  {
		daoComputer.deleteComputer(id);
	}

	public void updateComputer(String[] args) throws UpdateComputerError, ValidatorException{

		Long id = Long.parseLong(args[0]);
		Optional<Computer> optcomputer = daoComputer.getCompDetails(id);
		if(!optcomputer.isPresent()) {
			throw new UpdateComputerError("Computer not in base");
		}
		Computer computer = optcomputer.get();
		switch (args[1]) {
		case NAME:
			computer.setName(args[2]);
			break;
		case INTRODUCED:
			computer.setIntroduced(TimeStampMapper.stringToTimestamp(args[2]).get());
			break;
		case DISCONTINUED:
			Timestamp t = TimeStampMapper.stringToTimestamp(args[2]).get();
			computer.setDiscontinued(t);
			break;
		case COMPANY_ID:
			computer.setCompanyId(Long.parseLong(args[2]));
			break;
		default:
			log.error("Not a valid column : "+ args[1]);
			return;
		}
		Validator.computerValidator(computer);
		daoComputer.updateComputer(computer);
	}
	
	public void updateComputer(Computer computer) throws UpdateComputerError, ValidatorException{
		Validator.computerValidator(computer);
		daoComputer.updateComputer(computer);
	}
	

}
