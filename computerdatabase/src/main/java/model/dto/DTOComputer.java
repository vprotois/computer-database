package model.dto;


public class DTOComputer {

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	private long id;
	private String name;
	private String introduced, discontinued;
	private String company;
	
	
	@Override
	public String toString() {
		return "id : "+id
				+ " name : " + name
				+ " introduced : "+introduced
				+ " diconstinued : "+discontinued
				+ " company : "+ company;
	}
}
