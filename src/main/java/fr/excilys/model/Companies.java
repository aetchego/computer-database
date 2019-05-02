package fr.excilys.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Companies {

	private List<Company> companies = new ArrayList<>();

	public List<Company> getCompanies() {
		return companies;
	}

	public void addCompany(Company company) {
		this.companies.add(company);
	}

	@Override
	public String toString() {
		return "Companies [companies=" + companies + "\n ]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(companies);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Companies)) {
			return false;
		}
		Companies other = (Companies) obj;
		return Objects.equals(companies, other.companies);
	}
}
