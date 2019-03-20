package services;

import java.util.List;


import model.Company;
import model.Pages;
import persistance.DAOCompany;
import persistance.DAOFactory;

public class CompanyServices {

	public List<Company> listCompanies() {
		DAOCompany daoCompany = (DAOCompany) DAOFactory.createDAOcompany();
		List<Company> companies = daoCompany.getCompanies();
		return companies;
	}
	
	public Pages <Company> pageListCompanies() {
		DAOCompany daoCompany = (DAOCompany) DAOFactory.createDAOcompany();
		Pages <Company> pages = daoCompany.pageListCompany();
		return pages;
		
	}
}
