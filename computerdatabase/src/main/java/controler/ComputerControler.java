package controler;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Computer;
import model.ComputerBuilder;
import model.Page;
import persistance.DAOCompany;
import persistance.DAOComputer;
import persistance.DAOFactory;
import ui.InterfaceConsole;

public class ComputerControler {

	private static Logger log= LoggerFactory.getLogger(ComputerControler.class);
	
	public ComputerControler() {
		
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


	public void listComputer() {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		List<Computer> list = daoComputer.listComputers();
		InterfaceConsole.displayList(list);
	}
	
	public void pageListComputer() {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Page <Computer> list = daoComputer.pageListComputer();
		InterfaceControler.pageMenu(list);
	}

	
	public void showCompDetails(Long id) {
		DAOComputer daoComputer = (DAOComputer) DAOFactory.createDAOcomputer();
		Computer c = daoComputer.getCompDetails(id);
		if(c!=null) {
			InterfaceConsole.display(c);			
		}else
		{
			log.error("Computer "+id+" not in base");
		}
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
