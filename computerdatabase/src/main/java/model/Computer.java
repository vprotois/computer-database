package model;

import java.sql.Timestamp;

public class Computer {
	private Integer id;
	private String name;
	private Integer company_id;
	private Timestamp introduced;
	private Timestamp discontinued;
	
	public Computer() {
	}

	public Computer(int id, String name, int company_id, Timestamp introduced, Timestamp discontinued) {
		this.id = id;
		this.name = name;
		this.company_id = company_id;
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
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
