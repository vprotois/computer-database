package model;

import java.sql.Timestamp;

public class ComputerBuilder {

	private Long id;
	private String name;
	private Company company;
	private Timestamp introduced;
	private Timestamp discontinued;
	
	public ComputerBuilder(){
		
	}
	
	public Computer build() {
		return new Computer(id,name,company,introduced,discontinued);
	}
	
	public ComputerBuilder withId(Long i) {
		this.id = i;
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
		if (introduced == null || introduced.compareTo(t)>0) {
			return this;
		}
		this.discontinued = t;
		return this;
	}
	
	
}
