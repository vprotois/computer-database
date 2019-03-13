package controler;

import java.sql.SQLException;
import java.util.List;

import model.Company;
import persistance.DAOcompany;
import ui.InterfaceConsole;

public class CompanyControler {

	public void listCompanies() throws SQLException {
		DAOcompany daoCompany = new DAOcompany();
		List<Company> companies = daoCompany.getCompanies();
		InterfaceConsole.displayList(companies);
	}
}
