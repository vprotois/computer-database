package model;

public abstract class Entity {

	Long id;
	
	public Long getId() {
		return id;
	}
	
	public abstract String toString();
	
}
