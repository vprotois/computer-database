package services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.NotFoundException;
import mapper.DTOComputerMapper;
import model.Computer;
import model.ComputerBuilder;
import model.DTOComputer;
import model.Pages;
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


	public List<Computer> listComputer() {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		List<Computer> list = daoComputer.listComputers();
		return list;
	}
	
	public Pages <Computer> pageListComputer() {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Pages <Computer> list = daoComputer.pageListComputer(null,null);
		return list;
	}
	
	public Pages<Computer> getListComputer(Integer size, Integer index) {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Pages <Computer> list = daoComputer.pageListComputer(size,index);
		return list;
	}

	
	public DTOComputer getComputerDTO(Long id) throws NotFoundException {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Computer c = daoComputer.getCompDetails(id);
		if(c == null) {
			throw new NotFoundException();
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
