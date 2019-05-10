package com.excilys.model;

import java.util.Objects;

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
	
	public void setId(long id) {
		this.id = id;
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
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
	
}
