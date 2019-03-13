package controler;

import java.sql.SQLException;

import ui.InterfaceConsole;

public class InterfaceControler {
	
	private DBControler cont = new DBControler();

	public void start() {
		int menuInput = InputControler.getInputInt(1,6);
		Long input;
		String[] inputArgs;
		try {
			InterfaceConsole.displayMenu();
			switch (menuInput) {
			case 1:
				cont.listComputer();
				break;
			case 2:
				cont.listCompanies();
				break;
			case 3:
				input = InputControler.getInputLong();
				cont.showCompDetails(input);
				break;
			case 4:
				inputArgs = InputControler.getInputString(5);
				cont.buildComputer(inputArgs);
				break;
			case 5:
				inputArgs = InputControler.getInputString(3);
				cont.updateComputer(inputArgs);
				break;
			case 6:
				input = InputControler.getInputLong();
				cont.deleteComputer(input);
				break;
			default:
				System.out.println("bad input");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}


	}
	
}
