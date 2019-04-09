package services;

import java.util.List;
import java.util.Optional;


import model.Company;
import model.Pages;
import model.builders.PagesBuilder;
import persistance.DAOCompany;

public class CompanyServices {

	private DAOCompany daoCompany;
	
	public CompanyServices (DAOCompany daoCompany) {
		this.daoCompany = daoCompany;
	}
	
	public Optional<List<Company>> listCompanies() {
		Optional<List<Company>> companies = daoCompany.getCompanies();
		return companies;
	}
	
	public Optional<Pages<Company>> pageCompanies(Integer size, Integer index) {
		Optional<List<Company>> list = listCompanies();
		if(!list.isPresent()) {
			return Optional.empty();
		}
		
		Pages <Company> pages = new PagesBuilder<Company>()
								.withData(list.get())
								.withIndex(index)
								.withSize(size)
								.build();
		return Optional.of(pages);
		
	}
	
	public Optional<Company> getCompany(Long id){
		return daoCompany.getCompany(id);
	}
	
	public void deleteCompany(Long id) {
		daoCompany.deleteCompany(id);
	}
}
