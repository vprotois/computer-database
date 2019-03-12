package ui;

import java.sql.SQLException;

import controler.ConsoleControler;
import controler.InputControler;
import model.ComputerDB;

public class InterfaceConsole {

	private ConsoleControler cont;
	
	public InterfaceConsole() {
		cont = new ConsoleControler();
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

	public void start() {
		displayMenu();
		int menuInput; 
		Integer idInput;
		String stringInput1;
		String stringInput2;
		
		do {
			menuInput = InputControler.getInputInt(0,6);
			try {
				switch (menuInput) {
				case 0: break;
				case 1:
					cont.listComputer();
					break;
				case 2:
					cont.listCompanies();
					break;
				case 3:
					idInput = InputControler.getInputInt();
					cont.showCompDetails(idInput);
					break;
				case 4:
					cont.buildComp();
					break;
				case 5:
					idInput = InputControler.getInputInt();
					stringInput1 = InputControler.getInputString();
					stringInput2 = InputControler.getInputString();
					
				case 6:
					idInput = InputControler.getInputInt();
					cont.deleteComputer(idInput);
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
