package model;

public class CompanyFactory {

	private Long id;
	private String name;

	public CompanyFactory withId(Long id) {
		this.id = id;
		return this;
	}
	
	public CompanyFactory withName(String name) {
		this.name = name;
		return this;
	}
	
	public Company build() {
		return new Company(id,name);
	}
	
}
