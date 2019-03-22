package services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.ComputerNotFoundException;
import exception.CreateComputerError;
import exception.UpdateComputerError;
import mapper.DTOComputerMapper;
import mapper.TimeStampMapper;
import model.Computer;
import model.Pages;
import model.builders.ComputerBuilder;
import model.builders.PagesBuilder;
import model.dto.DTOComputer;
import persistance.DAOComputer;
import persistance.DAOFactory;

public class ComputerServices {

	public static Logger log= LoggerFactory.getLogger(ComputerServices.class);
	
	public ComputerServices() {
		
	}

	public void buildComputerWithId(String[] args) throws CreateComputerError {
		
		ComputerBuilder c = new ComputerBuilder()
				.withId(Long.parseLong(args[0]))
				.withName(args[1])
				.withIntroduced(TimeStampMapper.stringToTimestamp(args[2]))
				.withDiscontinued(TimeStampMapper.stringToTimestamp(args[3]))
				.withCompanyId(Long.parseLong(args[4]));
		
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		daoComputer.createComputer(c.build());

	}
	
	
	public void buildComputer(String name,String introduced,String discontinued,String companyId) throws CreateComputerError {
		
		ComputerBuilder c = new ComputerBuilder()
				.withName(name)
				.withIntroduced(TimeStampMapper.stringToTimestamp(introduced))
				.withDiscontinued(TimeStampMapper.stringToTimestamp(discontinued))
				.withCompanyId(Long.parseLong(companyId));
		
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		daoComputer.createComputer(c.build());

	}
	
	public void addComputer(Computer c) throws CreateComputerError {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		daoComputer.createComputer(c);
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
	
	
	public Optional<Pages<DTOComputer>> pagesDTOComputer(Integer size, Integer index){
		Optional<List<DTOComputer>> list = listDTOComputer();
		if(list.isPresent()) {
			Pages<DTOComputer> pages = new PagesBuilder<DTOComputer>()
					.withData(list.get())
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
	
	public Optional<Pages <DTOComputer>> pagesComputerWithName(String name, Integer size, Integer index) {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Optional<List<Computer>> list = daoComputer.getListFromName(name);
		if(!list.isPresent()) {
			return Optional.empty();
		}
		else {			
			
			List<DTOComputer> listDTO = list.get().stream()
										.map(c -> DTOComputerMapper.mapComputerToDTO(c))
										.collect(Collectors.toList());
			
			Pages <DTOComputer> pages = new PagesBuilder<DTOComputer>()
					.withData(listDTO)
					.withIndex(index)
					.withSize(size)
					.build();
			return Optional.of(pages);
		}
	}

	
	public DTOComputer getComputerDTO(Long id) throws ComputerNotFoundException {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Optional<Computer> c = daoComputer.getCompDetails(id);
		if(!c.isPresent()) {
			throw new ComputerNotFoundException();
		}
		DTOComputer dto = DTOComputerMapper.mapComputerToDTO(c.get());
		return dto;
	}
	
	

	public void deleteComputer(Long id)  {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		daoComputer.deleteComputer(id);
	}

	public void updateComputer(String[] args) throws UpdateComputerError{
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Long id = Long.parseLong(args[0]);
		Optional<Computer> optc = daoComputer.getCompDetails(id);
		if(!optc.isPresent()) {
			throw new UpdateComputerError();
		}
		Computer c = optc.get();
		switch (args[1]) {
		case "name":
			c.setName(args[2]);
			break;
		case "introduced":
			c.setIntroduced(TimeStampMapper.stringToTimestamp(args[2]));
			break;
		case "discontinued":
			Timestamp t = TimeStampMapper.stringToTimestamp(args[2]);
			if(t.compareTo(c.getIntroduced())> 0) {
				c.setDiscontinued(t);
			}
			break;
		case "company_id":
			c.setCompanyId(Long.parseLong(args[2]));
			break;
		default:
			log.error("Not a valid column");
			return;
		}
		daoComputer.updateComputer(c);
	}
	

}
