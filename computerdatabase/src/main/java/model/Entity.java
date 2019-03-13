package model;

public abstract class Entity {

	Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public abstract String toString();
	
}
