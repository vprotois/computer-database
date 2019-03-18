package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyBuilder {

	private Long id;
	private String name;
	
	private static Logger log= LoggerFactory.getLogger(CompanyBuilder.class);

	public CompanyBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	
	public CompanyBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public Company build() {
		if(id == null) {
			log.warn("id can't be null, null is returned");
			return null;
		}
		return new Company(id,name);
	}
	
}
