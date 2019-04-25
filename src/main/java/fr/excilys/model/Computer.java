package fr.excilys.model;

import java.sql.Date;

public class Computer {

	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private String brand;
	private Integer companyId;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String companyName) {
		this.brand = companyName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Date date) {
		this.discontinued = date;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	@Override
	public String toString() {
		return "[" + id + "] [ name = " + name + " ] [ introduced = " + introduced + " ] [ discontinued = " + discontinued
				+ " ] [ brand = " + brand + " ]";
	}
	
	public String toLittleString() {
		return "[" + id + "] [ name = " + name + " ] [ brand = " + brand +" ]";
	}
}
