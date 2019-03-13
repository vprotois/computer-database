package model;

import java.sql.Timestamp;

public class Computer {
	private Long id;
	private String name;
	private Company company;
	private Timestamp introduced;
	private Timestamp discontinued;
	
	public Computer() {
	}

	public Computer(Long id, String name, Company company, Timestamp introduced, Timestamp discontinued) {
		this.id = id;
		this.name = name;
		this.company = company;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}
	
	@Override
	public String toString() {
		return "'" +this.getId().toString() + "','" +
				this.getName() + "','" +
				this.getIntroduced() + "','" +
				this.getDiscontinued() + "','" +
				this.getCompany_id()+"'";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}
	
	public Integer getCompany_id() {
		return company.getId();
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Timestamp getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}

	public Timestamp getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}

	
	
	
	
}
