package model.builders;

import java.sql.Timestamp;


import model.Company;
import model.Computer;


public class ComputerBuilder {

	
	private Long id;
	private String name;
	private Company company;
	private Timestamp introduced,discontinued;
	private Long companyId;
	
	public ComputerBuilder(){
		
	}
	
	public Computer build() {
		return new Computer(id,name,company,introduced,discontinued,companyId);
	}
	
	public ComputerBuilder withId(Long i) {
		this.id = new Long(i);
		return this;
	}
	
	public ComputerBuilder withCompanyId(Long i) {
		this.companyId = new Long(i);
		return this;
	}
	
	public ComputerBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public ComputerBuilder withCompany(Company co) {
		this.company = co;
		return this;
	}
	
	public ComputerBuilder withIntroduced(Timestamp t) {
		this.introduced = t;
		return this;
	}
	
	public ComputerBuilder withDiscontinued(Timestamp t) {
		this.discontinued = t;
		return this;
	}
	
	
}
