package model;

import java.sql.Timestamp;

public class ComputerFactory {

	private Long id;
	private String name;
	private Company company;
	private Timestamp introduced,discontinued;
	private Long companyId;
	
	public ComputerFactory(){
		
	}
	
	public Computer build() {
		return new Computer(id,name,company,introduced,discontinued,companyId);
	}
	
	public ComputerFactory withId(Long i) {
		this.id = new Long(i);
		return this;
	}
	
	public ComputerFactory withCompanyId(Long i) {
		this.companyId = new Long(i);
		return this;
	}
	
	public ComputerFactory withName(String name) {
		this.name = name;
		return this;
	}
	
	public ComputerFactory withCompany(Company co) {
		this.company = co;
		return this;
	}
	
	public ComputerFactory withIntroduced(Timestamp t) {
		this.introduced = t;
		return this;
	}
	
	public ComputerFactory withDiscontinued(Timestamp t) {
		if (t == null || introduced == null || introduced.compareTo(t)>0) {
			return this;
		}
		this.discontinued = t;
		return this;
	}
	
	
}
