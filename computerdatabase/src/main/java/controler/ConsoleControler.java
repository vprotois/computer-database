package controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.ComputerBuilder;
import model.ComputerDB;

public class ConsoleControler {

	private ComputerDB db;

	public ConsoleControler() {
		db = new ComputerDB();
	}
	
	
	
	public void buildComp() throws SQLException {
		ComputerBuilder c = new ComputerBuilder();
		System.out.println("ID :");
		c.withId(InputControler.getInputInt());
		System.out.println("Name :");
		c.withName(InputControler.getInputStringNotNull());
		System.out.println("Introduced (can be null) :");
		Long l1 = InputControler.getInputLong();
		if (l1!=null) {
			c.withIntroduced(new Timestamp(l1));
			System.out.println("Discontinued (can be null/have to be greater than Introduced) :");
			Long l2 = InputControler.getInputLong();
			if (l1.compareTo(l2)<=0) {
				c.withIntroduced(new Timestamp(l2));
			}
		}
		System.out.println("Company id :");
		c.withId(InputControler.getInputInt());
		db.createComputer(c.build());
	}
	
	public void listComputer() throws SQLException {
		ResultSet results = db.listComputers();
		while (results.next()) {
			System.out.println(results.getInt("id")+" "
					+ results.getString("name") + " "
					+ results.getTimestamp("introduced") + " "
					+ results.getTimestamp("discontinued") + " "
					+ results.getInt("company_id")
					);
		}
	}
	
	public void listCompanies() throws SQLException {
		ResultSet results = db.listCompanies(); 
		while (results.next()) {
			System.out.println(results.getInt("id")+" "
					+ results.getString("name")
					);
		}
	}
	
	public void showCompDetails(int i) throws SQLException {
		ResultSet results = db.getCompDetails(i); 
		while (results.next()) {
			System.out.println(results.getInt("id")+" "
					+ results.getString("name") + " "
					+ results.getTimestamp("introduced") + " "
					+ results.getTimestamp("discontinued") + " "
					+ results.getInt("company_id")
					);
		}
	}
	
	public void deleteComputer(int id) throws SQLException {
		db.deleteComputer(id);
	}
	
	
}
