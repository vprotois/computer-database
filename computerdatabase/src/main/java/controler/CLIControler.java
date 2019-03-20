package controler;

import java.util.List;

import exception.ComputerNotFoundException;
import model.Pages;
import model.dto.DTOComputer;
import services.CompanyServices;
import services.ComputerServices;
import ui.InterfaceConsole;

public class CLIControler {

	private ComputerServices computerServices = new ComputerServices();
	private CompanyServices  companyServices  = new CompanyServices();

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
		List<?> list;
		Pages<?> pages;
		switch (menuInput) {
		case LIST_COMPUTERS:
			list = computerServices.listComputer();
			InterfaceConsole.displayList(list);
			break;
		case LIST_COMPANIES:
			list = companyServices.listCompanies();
			InterfaceConsole.displayList(list);
			break;
		case SHOW_DETAILS_COMPUTER:
			input = InputControler.getInputLong();
			showDetailsComputer(input);
			break;
		case CREATE_COMPUTER:
			inputArgs = InputControler.getInputString(5);
			computerServices.buildComputer(inputArgs);
			break;
		case UPDATE_COMPUTER:
			inputArgs = InputControler.getInputString(3);
			computerServices.updateComputer(inputArgs);
			break;
		case DELETE_COMPUTER:
			input = InputControler.getInputLong();
			computerServices.deleteComputer(input);
			break;
		case LIST_COMPUTERS_PAGES:
			pages = computerServices.pagesComputer(null,null);
			CLIControler.pageMenu(pages);
			break;
		case LIST_COMPANIES_PAGES:
			pages = companyServices.pageCompanies(null,null);
			CLIControler.pageMenu(pages);
			break;
		default:
			break;
		}
	}

	private void showDetailsComputer(Long input) {
		DTOComputer c;
		try {
			c = computerServices.getComputerDTO(input);
			InterfaceConsole.display(c);
		} catch (ComputerNotFoundException e) {
			InterfaceConsole.display("Computer not in base");
		}
	}
	
	
	
	public static <T> void pageMenu(Pages<T> pages) {
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
