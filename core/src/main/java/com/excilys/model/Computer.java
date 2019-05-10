package com.excilys.model;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@Column(name = "introduced",nullable = true)
	private Timestamp introduced;
	
	@Column(name = "discontinued",nullable = true)
	private Timestamp discontinued;
	
	public Computer() {
	}

	public Computer(Long id, String name, Company company, Timestamp introduced, Timestamp discontinued,Long companyId) {
		this.id = id;
		this.name = name;
		this.company = company;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(company, discontinued, id, introduced, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		return Objects.equals(company, other.company) && Objects.equals(discontinued, other.discontinued)
				&& Objects.equals(id, other.id) && Objects.equals(introduced, other.introduced)
				&& Objects.equals(name, other.name);
	}	
	
	
	@Override
	public String toString() {
		return "'" +this.getId()+ "','" +
				this.getName() + "','" +
				this.getIntroduced() + "','" +
				this.getDiscontinued() + "','" +
				this.getCompanyId()+"'";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}

	public Timestamp getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}

	public Timestamp getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}

	public Long getCompanyId() {
		if(this.company==null)
			return null;
		return this.company.getId();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
