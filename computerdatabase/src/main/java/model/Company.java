package model;

public class Company {

	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	
	public Company(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String toString() {
		return ""+id+" : "+name;
	}
	
	
}
