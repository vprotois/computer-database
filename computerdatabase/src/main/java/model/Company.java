package model;

public class Company extends Entity {


	private String name;
	
	public String getName() {
		return name;
	}
	
	
	public Company(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String toString() {
		return ""+id+" : "+name;
	}
	
	
}
