package services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.ComputerNotFoundException;
import exception.CreateComputerError;
import exception.UpdateComputerError;
import mapper.DTOComputerMapper;
import model.Company;
import model.Computer;
import model.Pages;
import model.builders.ComputerBuilder;
import model.builders.PagesBuilder;
import model.dto.DTOComputer;
import persistance.DAOCompany;
import persistance.DAOComputer;
import persistance.DAOFactory;

public class ComputerServices {

	private static Logger log= LoggerFactory.getLogger(ComputerServices.class);
	
	public ComputerServices() {
		
	}

	public void buildComputer(String[] args) throws CreateComputerError {
		
		ComputerBuilder c = new ComputerBuilder()
				.withId(Long.parseLong(args[0]))
				.withName(args[1])
				.withIntroduced(stringToTimestamp(args[2]))
				.withDiscontinued(stringToTimestamp(args[3]))
				.withCompanyId(Long.parseLong(args[4]));
		
		DAOCompany daoCompany =  (DAOCompany) DAOFactory.createDAOcompany();
		Optional<Company> cy =  daoCompany.getCompany(Long.parseLong(args[4]));
		if(cy.isPresent()) {
			c = c.withCompany(cy.get());
		}
		
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		daoComputer.createComputer(c.build());

	}

	public static Timestamp stringToTimestamp(String stringDate){
		try {
			if(stringDate.equals("null")) {
				return null;
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
				Date date = dateFormat.parse(stringDate);
				Timestamp timeStampDate = new Timestamp(date.getTime());
				return timeStampDate;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("Error when parsing Timestamp format");
		}
		return null;
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
			c.setIntroduced(stringToTimestamp(args[2]));
			break;
		case "discontinued":
			Timestamp t = stringToTimestamp(args[2]);
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
