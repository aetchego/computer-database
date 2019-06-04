package fr.excilys.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Companies {

	private Map<Integer, Company> companiesMap = new HashMap<>();

	public void setCompaniesMap(List<Company> companies) {
		this.companiesMap = companies.stream().collect(Collectors.toMap(Company::getId, c -> c));
	}

	public Map<Integer, Company> getCompaniesMap() {
		return companiesMap;
	}

	public List<Company> getCompaniesList() {
		return this.companiesMap.values().stream().collect(Collectors.toList());
	}

	public void delete(int id) {
		if (companiesMap.containsKey(id))
			companiesMap.remove(id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(companiesMap);
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
		return Objects.equals(companiesMap, other.companiesMap);
	}

	@Override
	public String toString() {
		return "Companies [companiesMap=" + companiesMap + "]";
	}

}
