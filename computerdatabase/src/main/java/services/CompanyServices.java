package services;

import java.util.List;


import model.Company;
import model.Pages;
import model.builders.PagesBuilder;
import persistance.DAOCompany;
import persistance.DAOFactory;

public class CompanyServices {

	public List<Company> listCompanies() {
		DAOCompany daoCompany = (DAOCompany) DAOFactory.createDAOcompany();
		List<Company> companies = daoCompany.getCompanies();
		return companies;
	}
	
	public Pages <Company> pageCompanies(Integer size, Integer index) {
		List<Company> list = listCompanies();
		Pages <Company> pages = new PagesBuilder<Company>()
								.withData(list)
								.withIndex(index)
								.withSize(size)
								.build();
		return pages;
		
	}
}
