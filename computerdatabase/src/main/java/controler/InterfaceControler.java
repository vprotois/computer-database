package controler;

import ui.InterfaceConsole;

public class InterfaceControler {

	private ComputerControler CompuCont = new ComputerControler();
	private CompanyControler CompaCont = new CompanyControler();

	private static final int ABORT = 0;
	private static final int LIST_COMPUTERS = 1;
	private static final int LIST_COMPANIES = 2;
	private static final int SHOW_DETAILS_COMPUTER = 3;
	private static final int CREATE_COMPUTER = 4;
	private static final int UPDATE_COMPUTER = 5;
	private static final int DELETE_COMPUTER = 6;
	
	
	public void start() {
		Long input;
		String[] inputArgs;
		int menuInput = 0; 
		do {
			InterfaceConsole.displayMenu();
			menuInput = InputControler.getInputInt(0,6);
			switch (menuInput) {
			case ABORT:
				return;
			case LIST_COMPUTERS:
				CompuCont.listComputer();
				break;
			case LIST_COMPANIES:
				CompaCont.listCompanies();
				break;
			case SHOW_DETAILS_COMPUTER:
				input = InputControler.getInputLong();
				CompuCont.showCompDetails(input);
				break;
			case CREATE_COMPUTER:
				inputArgs = InputControler.getInputString(5);
				CompuCont.buildComputer(inputArgs);
				break;
			case UPDATE_COMPUTER:
				inputArgs = InputControler.getInputString(3);
				CompuCont.updateComputer(inputArgs);
				break;
			case DELETE_COMPUTER:
				input = InputControler.getInputLong();
				CompuCont.deleteComputer(input);
				break;
			default:
				InterfaceConsole.display("bad input");
			}
		}while(menuInput!=0);


	}

}
