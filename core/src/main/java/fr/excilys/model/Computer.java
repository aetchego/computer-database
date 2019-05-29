package fr.excilys.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {

	@Id
	private Integer id;
	private String name;
	private Date introduced;
	private Date discontinued;
	@OneToOne
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

	public Integer getId() {
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

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
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
