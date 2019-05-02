package com.excilys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.model.Company;
import com.excilys.model.Pages;
import com.excilys.builders.PagesBuilder;
import com.excilys.dao.DAOCompany;

@Service
public class CompanyServices {

	@Autowired
	private DAOCompany daoCompany;

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
