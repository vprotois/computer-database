package controler;

import model.Page;
import ui.InterfaceConsole;

public class InterfaceControler {

	private ComputerControler computerControler = new ComputerControler();
	private CompanyControler companyControler = new CompanyControler();

	private static final int ABORT = 0;
	private static final int LIST_COMPUTERS = 1;
	private static final int LIST_COMPANIES = 2;
	private static final int SHOW_DETAILS_COMPUTER = 3;
	private static final int CREATE_COMPUTER = 4;
	private static final int UPDATE_COMPUTER = 5;
	private static final int DELETE_COMPUTER = 6;
	private static final int LIST_COMPUTERS_PAGES= 7;
	private static final int LIST_COMPANIES_PAGES = 8;
	
	
	private static final int PREVIOUS_PAGE = 1;
	private static final int NEXT_PAGE = 2;
	
	public void start() {
		int menuInput = 0; 
		do {
			InterfaceConsole.displayMenu();
			menuInput = InputControler.getInputInt(0,8);
			menu(menuInput);
		}while(menuInput!=ABORT);


	}

	private void menu(int menuInput) {
		Long input;
		String[] inputArgs;
		switch (menuInput) {
		case LIST_COMPUTERS:
			computerControler.listComputer();
			break;
		case LIST_COMPANIES:
			companyControler.listCompanies();
			break;
		case SHOW_DETAILS_COMPUTER:
			input = InputControler.getInputLong();
			computerControler.showCompDetails(input);
			break;
		case CREATE_COMPUTER:
			inputArgs = InputControler.getInputString(5);
			computerControler.buildComputer(inputArgs);
			break;
		case UPDATE_COMPUTER:
			inputArgs = InputControler.getInputString(3);
			computerControler.updateComputer(inputArgs);
			break;
		case DELETE_COMPUTER:
			input = InputControler.getInputLong();
			computerControler.deleteComputer(input);
			break;
		case LIST_COMPUTERS_PAGES:
			computerControler.pageListComputer();
			break;
		case LIST_COMPANIES_PAGES:
			companyControler.pageListCompanies();
			break;
		default:
			break;
		}
	}
	
	
	
	public static <T> void pageMenu(Page<T> pages) {
		Integer pageInput;
		do {
			InterfaceConsole.displayList(pages.getData());
			InterfaceConsole.displayPageMenu();			
			pageInput = InputControler.getInputInt(0,2);
			switch(pageInput) {
			case PREVIOUS_PAGE:
				pages.previousPage();
				break;
			case NEXT_PAGE:
				pages.nextPage();
				break;
			}
		}while (pageInput != 0);
	}

}
