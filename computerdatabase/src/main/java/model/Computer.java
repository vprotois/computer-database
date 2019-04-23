package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {
	
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@Column(name = "introduced",nullable = true)
	private Timestamp introduced;
	
	@Column(name = "introduced",nullable = true)
	private Timestamp discontinued;
	
	@Column(name = "company_id")
	private Long companyId;
	
	public Computer() {
	}

	public Computer(Long id, String name, Company company, Timestamp introduced, Timestamp discontinued,Long companyId) {
		this.id = id;
		this.name = name;
		this.company = company;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Computer)) {
			return false;
		}
		return idEquals(o) 
			&& nameEquals(o)
			&& companyEquals(o)
			&& introducedEquals(o)
			&& discontinuedEquals(o)
			&& companyIdEquals(o);
				
	}

	private boolean idEquals(Object o) {
		if (this.id == null)
			return (((Computer) o).id ==null);
		else
			return this.id.equals( ((Computer) o).id);
	}
	
	private boolean nameEquals(Object o) {
		if (this.name == null)
			return (((Computer) o).name==null);
		else
			return this.name.equals( ((Computer) o).name);
	}
	
	private boolean companyEquals(Object o) {
		if (this.company == null)
			return (((Computer) o).company==null);
		else
			return this.company.equals( ((Computer) o).company);
	}
	
	private boolean introducedEquals(Object o) {
		if (this.introduced== null)
			return (((Computer) o).introduced==null);
		else
			return this.introduced.equals( ((Computer) o).introduced);
	}
	
	private boolean discontinuedEquals(Object o) {
		if (this.discontinued== null)
			return (((Computer) o).discontinued==null);
		else
			return this.discontinued.equals( ((Computer) o).discontinued);
	}
	
	private boolean companyIdEquals(Object o) {
		if (this.companyId== null)
			return (((Computer) o).companyId==null);
		else
			return this.companyId.equals( ((Computer) o).companyId);
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
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = new Long(id);
	}
	
	
	
	
}
