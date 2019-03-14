package controler;

import java.util.List;

import model.Company;
import persistance.DAOCompany;
import persistance.DAOFactory;
import ui.InterfaceConsole;

public class CompanyControler {

	public void listCompanies() {
		DAOCompany daoCompany = (DAOCompany) DAOFactory.createDAOcompany();
		List<Company> companies = daoCompany.getCompanies();
		InterfaceConsole.displayList(companies);
	}
}
