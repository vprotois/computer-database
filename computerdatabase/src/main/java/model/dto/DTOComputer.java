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

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	private long id;
	private String name;
	private String introduced, discontinued;
	private long companyId;
	
	@Override
	public String toString() {
		return "id : "+id
				+ " name : " + name
				+ " introduced : "+introduced
				+ " diconstinued : "+discontinued
				+ " companyId : "+ companyId;
	}
}
