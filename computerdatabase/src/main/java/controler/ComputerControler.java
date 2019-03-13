package controler;

import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import model.Computer;
import model.ComputerBuilder;
import persistance.DAOcompany;
import persistance.DAOcomputer;
import ui.InterfaceConsole;

public class ComputerControler {

	public ComputerControler() {
		
	}

	public void buildComputer(String[] args) throws SQLException {
		ComputerBuilder c = new ComputerBuilder()
				.withId(Long.parseLong(args[0]))
				.withName(args[1])
				.withIntroduced(stringToTimestamp(args[2]))
				.withDiscontinued(stringToTimestamp(args[3]))
				.withCompanyId(Long.parseLong(args[4]));
		
		DAOcompany daoCompany =  new DAOcompany();
		c = c.withCompany(daoCompany.getCompany(Long.parseLong(args[4])));
		DAOcomputer daoComputer = new DAOcomputer();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public void listComputer() throws SQLException {
		DAOcomputer daoComputer = new DAOcomputer();
		List<Computer> list = daoComputer.listComputers();
		InterfaceConsole.displayList(list);
	}

	
	public void showCompDetails(Long i) throws SQLException {
		DAOcomputer daoComputer = new DAOcomputer();
		Computer c = daoComputer.getCompDetails(i);
		InterfaceConsole.display(c);
	}

	public void deleteComputer(Long id) throws SQLException {
		DAOcomputer daoComputer = new DAOcomputer();
		daoComputer.deleteComputer(id);
	}

	public void updateComputer(String[] args) throws SQLException {
		DAOcomputer daoComputer = new DAOcomputer();
		daoComputer.updateComputer(Long.parseLong(args[0]),args[1],args[2]);
	}

}
