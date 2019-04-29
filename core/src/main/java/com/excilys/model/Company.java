package com.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company{

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@Column(name = "name")
    private String name;
		
	public String getName() {
		return name;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = new Long(id);
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
