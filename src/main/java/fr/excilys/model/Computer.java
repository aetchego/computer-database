package fr.excilys.model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Computer {

	private Integer id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company company;

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

	public Company getCompany() {
		return company;
	}

	public void setCompany(String name, Companies companies) {
		List<Company> comp = companies.getCompanies();
		for (Company e : comp) {
			if (e.getName().equals(name)) {
				this.company = e;
			}
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(company, discontinued, id, introduced, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Computer)) {
			return false;
		}
		Computer other = (Computer) obj;
		return Objects.equals(company, other.company) && Objects.equals(discontinued, other.discontinued)
				&& id == other.id && Objects.equals(introduced, other.introduced) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}
}
