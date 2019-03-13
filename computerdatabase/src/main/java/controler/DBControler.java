package controler;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Set;

import model.Company;
import model.ComputerBuilder;
import model.ComputerDB;
import ui.InterfaceConsole;

public class DBControler {

	private ComputerDB db;

	public DBControler() {
		db = new ComputerDB();

	}




	public void buildComputer(String[] args) throws SQLException {
		ComputerBuilder c = new ComputerBuilder()
				.withId(Long.parseLong(args[0]))
				.withName(args[1])
				.withIntroduced(stringToTimestamp(args[2]))
				.withDiscontinued(stringToTimestamp(args[3]))
				.withCompany(db.getCompany(Integer.parseInt(args[4])));
		db.createComputer(c.build());

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
		ResultSet results = db.listComputers();
		ArrayList<String> list = new ArrayList<String>();
		while (results.next()) {
			list.add(results.getInt("id")+" "
					+ results.getString("name") + " "
					+ results.getTimestamp("introduced") + " "
					+ results.getTimestamp("discontinued") + " "
					+ results.getInt("company_id")
					);
		}
		InterfaceConsole.displayList(list);

	}

	public void listCompanies() throws SQLException {
		Hashtable<Integer,Company> companies = db.getCompanies();
		Set<Integer> keys = companies.keySet();
		for (Integer key : keys) {
			System.out.println(companies.get(key));
		}
	}

	public void showCompDetails(Long i) throws SQLException {
		ResultSet results = db.getCompDetails(i);
		ArrayList<String> list = new ArrayList<String>();
		while (results.next()) {
			list.add(results.getInt("id")+" "
					+ results.getString("name") + " "
					+ results.getTimestamp("introduced") + " "
					+ results.getTimestamp("discontinued") + " "
					+ results.getInt("company_id")
					);
		}
		InterfaceConsole.displayList(list);
	}

	public void deleteComputer(Long id) throws SQLException {
		db.deleteComputer(id);
	}

	public void updateComputer(String[] args) throws SQLException {
		db.updateComputer(Long.parseLong(args[0]),args[1],args[2]);
	}

}
