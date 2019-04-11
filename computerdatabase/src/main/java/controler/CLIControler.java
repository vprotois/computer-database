package controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.ComputerNotFoundException;
import exception.CreateComputerError;
import exception.UpdateComputerError;
import exception.ValidatorException;
import model.Company;
import model.Computer;
import model.Pages;
import model.dto.DTOComputer;
import services.CompanyServices;
import services.ComputerServices;
import ui.InterfaceConsole;

@Component
public class CLIControler {	
	
	@Autowired
	private CompanyServices companyServices;
	
	@Autowired
	private ComputerServices computerServices;
	
	private static final int ABORT = 0;
	private static final int LIST_COMPUTERS = 1;
	private static final int LIST_COMPANIES = 2;
	private static final int SHOW_DETAILS_COMPUTER = 3;
	private static final int CREATE_COMPUTER = 4;
	private static final int UPDATE_COMPUTER = 5;
	private static final int DELETE_COMPUTER = 6;
	private static final int LIST_COMPUTERS_PAGES= 7;
	private static final int LIST_COMPANIES_PAGES = 8;
	private static final int DELETE_COMPANY = 9;
		
	private static final int PREVIOUS_PAGE = 1;
	private static final int NEXT_PAGE = 2;
	
	public CLIControler() {
	}
	
	
	public void start() {
		int menuInput = 0; 
		do {
			InterfaceConsole.displayMenu();
			menuInput = InputControler.getInputInt(0,9);
			menu(menuInput);
		}while(menuInput!=ABORT);

	}

	private void menu(int menuInput) {
		Long input;
		String[] inputArgs;
		switch (menuInput) {
		case LIST_COMPUTERS:
			listComputer();
			break;
		case LIST_COMPANIES:
			listCompany();
			break;
		case SHOW_DETAILS_COMPUTER:
			input = InputControler.getInputLong();
			showDetailsComputer(input);
			break;
		case CREATE_COMPUTER:
			inputArgs = InputControler.getInputString(5);
			createComputer(inputArgs);
			break;
		case UPDATE_COMPUTER:
			inputArgs = InputControler.getInputString(3);
			updateComputer(inputArgs);
			break;
		case DELETE_COMPUTER:
			input = InputControler.getInputLong();
			deleteComputer(input);
			break;
		case LIST_COMPUTERS_PAGES:
			listPagesComputer();
			break;
		case LIST_COMPANIES_PAGES:
			listCompanyPages();
			break;
		case DELETE_COMPANY:
			input = InputControler.getInputLong();
			companyServices.deleteCompany(input);
			break;
		default:
			break;
		}
	}


	private void deleteComputer(Long input) {
		try {
			computerServices.deleteComputer(input);
		} catch (UpdateComputerError e) {
			InterfaceConsole.display("Failed to delete computer : " + e.getMessage());
		}
	}

	private void updateComputer(String[] inputArgs) {
		try {
			computerServices.updateComputer(inputArgs);
			InterfaceConsole.display("ComputerUpdated");
		} catch (UpdateComputerError e) {
			InterfaceConsole.display("Failed to update computer :"+ e.getMessage());
		} catch (ValidatorException e) {
			InterfaceConsole.display(e.getMessage());
		}
	}

	private void createComputer(String[] inputArgs) {
		try {
			computerServices.buildComputerWithId(inputArgs);
			InterfaceConsole.display("ComputerCreated");
		} catch (CreateComputerError e) {
			InterfaceConsole.display("Failed to create computer :"+ e.getMessage());
		} catch (ValidatorException e) {
			InterfaceConsole.display(e.getMessage());
		}
	}

	private void listPagesComputer() {
		Optional<Pages<Computer>> pages = computerServices.pagesComputer(null,null);
		if(pages.isPresent()) {
			CLIControler.pageMenu(pages.get());			
		}
		else {
			InterfaceConsole.display("Pages not found");
		}
	}

	private void listCompanyPages() {
		Optional<Pages<Company>> pages = companyServices.pageCompanies(null,null);
		if(pages.isPresent()) {
			CLIControler.pageMenu(pages.get());
		}else {
			InterfaceConsole.display("Pages not found");
		}
		
	}

	private void listCompany() {
		Optional<List<Company>> list = companyServices.listCompanies();
		if(list.isPresent()) {
			InterfaceConsole.displayList(list.get());
		}else {
			InterfaceConsole.display("List not found");
		}
		
	}

	private void listComputer() {
		Optional<List<Computer>> list = computerServices.listComputer();
		if(list.isPresent()) {
			InterfaceConsole.displayList(list.get());			
		}else {
			InterfaceConsole.display("List not found");
		}
	}

	private void showDetailsComputer(Long input) {
		DTOComputer computer;
		try {
			computer = computerServices.getComputerDTO(input);
			InterfaceConsole.display(computer);
		} catch (ComputerNotFoundException e) {
			InterfaceConsole.display("Computer not in base");
		}
	}	
	
	public static <T> void pageMenu(Pages<T> pages) {
		Integer pageInput;
		do {
			InterfaceConsole.displayList(pages.getPageData());
			InterfaceConsole.displayPageMenu();			
			pageInput = InputControler.getInputInt(0,2);
			switch(pageInput) {
			case PREVIOUS_PAGE:
				pages.previousPage();
				break;
			case NEXT_PAGE:
				break;
			}
		}while (pageInput != 0);
	}

}
