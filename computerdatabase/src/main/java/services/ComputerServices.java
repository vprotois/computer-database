package services;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import persistance.DAOFactory;
import validator.Validator;

public class ComputerServices {

	public static Logger log= LoggerFactory.getLogger(ComputerServices.class);
	public static final String NAME = "name";
	public static final String INTRODUCED = "introduced";
	public static final String DISCONTINUED = "discontinued";
	public static final String COMPANY_ID = "company_id";
	public static final String EMPTY = "";
			
	public ComputerServices() {
		
	}

	public void buildComputerWithId(String[] args) throws CreateComputerError, ValidatorException {
		ComputerBuilder cBuilder = new ComputerBuilder()
				.withId(Optional.of(Long.parseLong(args[0])))
				.withName(Optional.of(args[1]))
				.withIntroduced(TimeStampMapper.stringToTimestamp(args[2]))
				.withDiscontinued(TimeStampMapper.stringToTimestamp(args[3]))
				.withCompanyId(Optional.of(Long.parseLong(args[4])));
		
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		
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
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Computer comp = compBuilder.build();
		Validator.computerValidator(comp);
		daoComputer.createComputer(comp);

	}
	
	public void addComputer(Computer comp) throws CreateComputerError, ValidatorException {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Validator.computerValidator(comp);
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
		Optional<List<Computer>> list = listComputer();
		if(list.isPresent()) {
			List<DTOComputer> listDTO =null;
			listDTO = mapAndSortList(order,asc, list, listDTO);
			if(listDTO == null) {
				return Optional.empty();
			}
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
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Optional<List<Computer>> list = daoComputer.listComputers();
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
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Optional<List<Computer>> list = daoComputer.getListFromName(name);
		if(!list.isPresent()) {
			return Optional.empty();
		}
		else {
			List<DTOComputer> listDTO =null;
			listDTO = mapAndSortList(order,asc, list, listDTO);
			if(listDTO == null) {
				return Optional.empty();
			}
			
			Pages <DTOComputer> pages = new PagesBuilder<DTOComputer>()
					.withData(listDTO)
					.withIndex(index)
					.withSize(size)
					.build();
			return Optional.of(pages);
		}
	}

	private List<DTOComputer> mapAndSortList(String order,Boolean asc, Optional<List<Computer>> list, List<DTOComputer> listDTO) {
		switch(order) {
		case EMPTY:
			listDTO = list.get().stream()
			.map(c -> DTOComputerMapper.mapComputerToDTO(c))
			.collect(Collectors.toList());				
			break;
		case NAME:
			listDTO = list.get().stream()
			.map(c -> DTOComputerMapper.mapComputerToDTO(c))
			.sorted((c1,c2) ->  c1.getName().compareTo(c2.getName()))
			.collect(Collectors.toList());				
			break;
		case INTRODUCED:
			listDTO = list.get().stream()
			.sorted((c1,c2) -> c1.getIntroduced() != null ? 
					(c2.getIntroduced()  != null ? c1.getIntroduced().compareTo(c2.getIntroduced()):0 ): Integer.MIN_VALUE)
			.map(c -> DTOComputerMapper.mapComputerToDTO(c))
			.collect(Collectors.toList());				
			break;
		case DISCONTINUED:
			listDTO = list.get().stream()
			.sorted((c1,c2) -> c1.getDiscontinued() != null ? c2.getDiscontinued() != null ? c1.getDiscontinued() .compareTo(c2.getDiscontinued()) : 0 :  Integer.MIN_VALUE)
			.map(c -> DTOComputerMapper.mapComputerToDTO(c))
			.collect(Collectors.toList());				
			break;
		case COMPANY_ID:
			listDTO = list.get().stream()
			.map(c -> DTOComputerMapper.mapComputerToDTO(c))
			.sorted((c1,c2) -> c1.getCompany()
							.compareTo(c2.getCompany()))
			.collect(Collectors.toList());				
			break;
		default:
		}
		if(asc) {
			Collections.reverse(listDTO);
		}
		return listDTO;
	}
	
	public DTOComputer getComputerDTO(Long id) throws ComputerNotFoundException {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Optional<Computer> c = daoComputer.getCompDetails(id);
		if(!c.isPresent()) {
			throw new ComputerNotFoundException("Computer not in base");
		}
		DTOComputer dto = DTOComputerMapper.mapComputerToDTO(c.get());
		return dto;
	}

	public void deleteComputer(Long id)  {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		daoComputer.deleteComputer(id);
	}

	public void updateComputer(String[] args) throws UpdateComputerError, ValidatorException{
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
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
			if(t.compareTo(computer.getIntroduced())> 0) {
				computer.setDiscontinued(t);
			}
			break;
		case COMPANY_ID:
			computer.setCompanyId(Long.parseLong(args[2]));
			break;
		default:
			log.error("Not a valid column");
			return;
		}
		Validator.computerValidator(computer);
		daoComputer.updateComputer(computer);
	}
	
	public void updateComputer(Computer computer) throws UpdateComputerError, ValidatorException{
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Validator.computerValidator(computer);
		daoComputer.updateComputer(computer);
	}
	

}
