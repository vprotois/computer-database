package model;

public class CompanyBuilder {

	private Long id;
	private String name;

	public CompanyBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	
	public CompanyBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public Company build() {
		
		return new Company(id,name);
	}
	
}
