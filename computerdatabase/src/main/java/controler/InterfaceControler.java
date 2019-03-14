package controler;

import java.sql.SQLException;

import ui.InterfaceConsole;

public class InterfaceControler {
	
	private ComputerControler CompuCont = new ComputerControler();
	private CompanyControler CompaCont = new CompanyControler();

	public void start() {
		
		Long input;
		String[] inputArgs;
		int menuInput = 0; 
		do {
			InterfaceConsole.displayMenu();
			menuInput = InputControler.getInputInt(0,6);
			try {
				switch (menuInput) {
				case 1:
					CompuCont.listComputer();
					break;
				case 2:
					CompaCont.listCompanies();
					break;
				case 3:
					input = InputControler.getInputLong();
					CompuCont.showCompDetails(input);
					break;
				case 4:
					inputArgs = InputControler.getInputString(5);
					CompuCont.buildComputer(inputArgs);
					break;
				case 5:
					inputArgs = InputControler.getInputString(3);
					CompuCont.updateComputer(inputArgs);
					break;
				case 6:
					input = InputControler.getInputLong();
					CompuCont.deleteComputer(input);
					break;
				default:
					InterfaceConsole.display("bad input");
				}
			} catch (SQLException e) {
				InterfaceConsole.display(e.getMessage());
			}
		}while(menuInput!=0);


	}
	
}
