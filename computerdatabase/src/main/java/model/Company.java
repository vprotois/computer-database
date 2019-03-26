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
	
	public Company() {
		
	}


	public String toString() {
		return ""+id+" : "+name;
	}
	
	@Override
	public boolean equals(Object o) {
		if(! (o instanceof Company)) {
			return false;
		}
		
		return idEquals(o) && nameEquals(o);
		
	}
	
	private boolean idEquals(Object o) {
		if (this.id == null)
			return (((Company) o).id ==null);
		else
			return this.id.equals( ((Company) o).id);
	}
	
	private boolean nameEquals(Object o) {
		if (this.name == null)
			return (((Company) o).name==null);
		else
			return this.name.equals( ((Company) o).name);
	}
	
	
}
