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

	public Company getCompany() {
		return company;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public int getId() {
		return id;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(company, discontinued, id, introduced, name);
	}

	public void setCompany(String name, Companies companies) {
		List<Company> comp = companies.getCompanies();
		for (Company e : comp) {
			if (e.getName().equals(name)) {
				this.company = e;
			}
		}
	}

	public void setDiscontinued(Date date) {
		this.discontinued = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}
}
