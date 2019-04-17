package model;

import java.util.ArrayList;
import java.util.List;

public final class Companies {

	private ArrayList<Company> companies = new ArrayList<>();

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
}
