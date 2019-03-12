package model;

import java.sql.Timestamp;

public class ComputerBuilder {

	private Computer c;
	
	public ComputerBuilder(){
		c = new Computer();
	}
	
	public Computer build() {
		if(c.getId() == null || c.getName() == null) {
			return null;
		}
		return c;
	}
	
	public void withId(Integer i) {
		c.setId(i);
	}
	
	public void withName(String name) {
		c.setName(name);
	}
	
	public void withCompanyID(Integer i) {
		c.setCompany_id(i);
	}
	
	public void withIntroduced(Timestamp t) {
		c.setIntroduced(t);
	}
	
	public void withDiscontinued(Timestamp t) {
		c.setDiscontinued(t);
	}
	
}
