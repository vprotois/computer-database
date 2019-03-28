package model.builders;

import java.sql.Timestamp;
import java.util.Optional;

import model.Company;
import model.Computer;


public class ComputerBuilder {

	
	private Optional<Long> id;
	private Optional<String> name;
	private Optional<Company> company;
	private Optional<Timestamp> introduced,discontinued;
	private Optional<Long> companyId;
	
	public ComputerBuilder(){
		
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
		if(this.company.isPresent()) {
			company = this.company.get();
		}
		if(this.introduced.isPresent()) {
			introduced = this.introduced.get();
		}
		if(this.discontinued.isPresent()) {
			introduced = this.discontinued.get();
		}
		if(this.companyId.isPresent()) {
			companyId = this.companyId.get();
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
		this.id = Optional.of(new Long(i));
		return this;
	}
	
	public ComputerBuilder withCompanyId(Long i) {
		this.companyId = Optional.of(new Long(i));
		return this;
	}
	
	public ComputerBuilder withName(String name) {
		this.name = Optional.of(name);
		return this;
	}
	
	public ComputerBuilder withCompany(Company co) {
		this.company = Optional.of(co);
		return this;
	}
	
	public ComputerBuilder withIntroduced(Timestamp t) {
		this.introduced = Optional.of(t);
		return this;
	}
	
	public ComputerBuilder withDiscontinued(Timestamp t) {
		this.discontinued = Optional.of(t);
		return this;
	}

	
	
}
