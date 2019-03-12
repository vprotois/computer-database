package ui;

import java.sql.SQLException;
import java.sql.Timestamp;

import controler.ConsoleControler;
import model.ComputerBuilder;
import model.ComputerDB;

public class InterfaceConsole {

	ComputerDB db;

	public InterfaceConsole() {
		db = new ComputerDB();
	}

	public static void displayMenu() {
		System.out.println("Choose input :");
		System.out.println("0 - abort");
		System.out.println("1 - List computers");
		System.out.println("2 - List companies");
		System.out.println("3 - Show computer details");
		System.out.println("4 - Create computer");
		System.out.println("5 - Update computer");
		System.out.println("6 - Delete computer");
	}

	public ComputerBuilder buildComp() {
		Timestamp t;
		ComputerBuilder c = new ComputerBuilder();
		System.out.println("ID :");
		c.withId(ConsoleControler.getInputInt());
		System.out.println("Name :");
		c.withName(ConsoleControler.getInputStringNotNull());
		System.out.println("Introduced (can be null) :");
		t = new Timestamp(ConsoleControler.getInputLong());
		c.withIntroduced(t);
		System.out.println("Discontinued (can be null) :");
		t = new Timestamp(ConsoleControler.getInputLong());
		c.withDiscontinued(t);
		System.out.println("Company id :");
		c.withId(ConsoleControler.getInputInt());
		return c;
	}

	public void start() {
		displayMenu();
		int menuInput; 
		int idInput;
		String stringInput1;
		String stringInput2;
		
		do {
			menuInput = ConsoleControler.getInputInt(0,6);
			try {
				switch (menuInput) {
				case 0: break;
				case 1:
					db.listComputers();
					break;
				case 2:
					db.listCompanies();
					break;
				case 3:
					idInput = ConsoleControler.getInputInt();
					db.showCompDetails(idInput);
					break;
				case 4:
					db.createComputer(buildComp().build());
					break;
				case 5:
					idInput = ConsoleControler.getInputInt();
					stringInput1 = ConsoleControler.getInputString();
					stringInput2 = ConsoleControler.getInputString();
				case 6:
					idInput = ConsoleControler.getInputInt();
					db.deleteComputer(idInput);
					break;
				default:
					System.out.println("Unexpected Error");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}while (menuInput != 0);
		

	}
}
