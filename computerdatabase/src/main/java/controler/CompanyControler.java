package controler;

import java.sql.SQLException;
import java.util.List;

import model.Company;
import persistance.DAOCompany;
import persistance.DAOFactory;
import ui.InterfaceConsole;

public class CompanyControler {

	public void listCompanies() throws SQLException {
		DAOCompany daoCompany = (DAOCompany) DAOFactory.create("company");
		List<Company> companies = daoCompany.getCompanies();
		InterfaceConsole.displayList(companies);
	}
}
