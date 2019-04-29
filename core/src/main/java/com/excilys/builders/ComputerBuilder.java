package com.excilys.builders;

import java.sql.Timestamp;
import java.util.Optional;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerBuilder {

	
	private Optional<Long> id;
	private Optional<String> name;
	private Optional<Company> company;
	private Optional<Timestamp> introduced,discontinued;
	private Optional<Long> companyId;
	
	public ComputerBuilder(){
		this.id = Optional.empty();
		this.name= Optional.empty();
		this.company= Optional.empty();
		this.introduced= Optional.empty();
		this.discontinued= Optional.empty();
		this.companyId= Optional.empty();
	}
	
	public Computer build() {
		Long id = null;
		String name = null;
		Company company = null;
		Timestamp introduced= null,discontinued= null;
		Long companyId = null;
		if(this.id.isPresent()) {
			id = this.id.get();
		}
		if(this.name.isPresent()) {
			name = this.name.get();
		}
		if(this.introduced.isPresent()) {
			introduced = this.introduced.get();
		}
		if(this.discontinued.isPresent()) {
			discontinued = this.discontinued.get();
		}
		if(this.companyId.isPresent()) {
			companyId = this.companyId.get();
		}
		if(this.company.isPresent()) {
			company = this.company.get();
			companyId = company.getId();
		}
		return new Computer(id,name,company,introduced,discontinued,companyId);
	}
	
	public ComputerBuilder withId(Optional<Long> i) {
		this.id = i;
		return this;
	}
	
	public ComputerBuilder withCompanyId(Optional<Long> i) {
		this.companyId = i;
		return this;
	}
	
	public ComputerBuilder withName(Optional<String> name) {
		this.name = name;
		return this;
	}
	
	public ComputerBuilder withCompany(Optional<Company> co) {
		this.company = co;
		return this;
	}
	
	public ComputerBuilder withIntroduced(Optional<Timestamp> t) {
		this.introduced = t;
		return this;
	}
	
	public ComputerBuilder withDiscontinued(Optional<Timestamp> t) {
		this.discontinued = t;
		return this;
	}
	
	public ComputerBuilder withId(Long i) {
		if(i != null) {
			this.id = Optional.of(new Long(i));
		}
		return this;
	}
	
	public ComputerBuilder withCompanyId(Long i) {
		if(i != null) {
			this.companyId = Optional.of(new Long(i));
		}
		return this;
	}
	
	public ComputerBuilder withName(String name) {
		if(name != null) {
			this.name = Optional.of(name);
		}
		return this;
	}
	
	public ComputerBuilder withCompany(Company co) {
		if (co != null) {
			this.company = Optional.of(co);
		}
		return this;
	}
	
	public ComputerBuilder withIntroduced(Timestamp t) {
		if(t != null) {
			this.introduced = Optional.of(t);
		}
		return this;
	}
	
	public ComputerBuilder withDiscontinued(Timestamp t) {
		if(t != null) {
			this.discontinued = Optional.of(t);
		}
		return this;
	}

	
	
}
