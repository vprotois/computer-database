package services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.ComputerNotFoundException;
import mapper.DTOComputerMapper;
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

	public void buildComputer(String[] args) {
		
		ComputerBuilder c = new ComputerBuilder()
				.withId(Long.parseLong(args[0]))
				.withName(args[1])
				.withIntroduced(stringToTimestamp(args[2]))
				.withDiscontinued(stringToTimestamp(args[3]))
				.withCompanyId(Long.parseLong(args[4]));
		
		DAOCompany daoCompany =  (DAOCompany) DAOFactory.createDAOcompany();
		c = c.withCompany(daoCompany.getCompany(Long.parseLong(args[4])));
		
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

	
	public List<DTOComputer> listDTOComputer(){
		List <DTOComputer> list = listComputer() .stream()
									.map( c -> DTOComputerMapper.mapComputerToDTO(c))
									.collect(Collectors.toList());
		return list;
	}
	
	
	public Pages<DTOComputer> pagesDTOComputer(Integer size, Integer index){
		List <DTOComputer> list = listDTOComputer();
		Pages<DTOComputer> pages = new PagesBuilder<DTOComputer>()
									.withData(list)
									.withIndex(index)
									.withSize(size)
									.build();
		return pages;
	}
	
	
	public List<Computer> listComputer() {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		List<Computer> list = daoComputer.listComputers();
		return list;
	}
	
	
	public Pages<Computer> pagesComputer(Integer size, Integer index) {
		List<Computer> list = listComputer();
		Pages <Computer> pages = new PagesBuilder<Computer>()
									.withData(list)
									.withIndex(index)
									.withSize(size)
									.build();
		return pages;
	}

	
	public DTOComputer getComputerDTO(Long id) throws ComputerNotFoundException {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Computer c = daoComputer.getCompDetails(id);
		if(c == null) {
			throw new ComputerNotFoundException();
		}
		DTOComputer dto = DTOComputerMapper.mapComputerToDTO(c);
		return dto;
	}
	
	

	public void deleteComputer(Long id)  {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		daoComputer.deleteComputer(id);
	}

	public void updateComputer(String[] args){
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Long id = Long.parseLong(args[0]);
		Computer c = daoComputer.getCompDetails(id);
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
