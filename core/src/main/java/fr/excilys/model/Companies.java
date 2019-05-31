package fr.excilys.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Companies {

	private List<Company> companiesList = new ArrayList<>();

	public void setCompaniesList(List<Company> companies) {
		this.companiesList = companies;
	}

	public List<Company> getCompaniesList() {
		return companiesList;
	}

	public void addCompany(Company company) {
		this.companiesList.add(company);
	}

//	public void removeCompany(Optional<Company> company) {
//		if (company.isPresent())
//			this.companiesList.remove(company.get());
//	}
	/*
	 * FAIRE HASHMAP POUR METTRE LES ID EN CLé ET SUPPRIMER FACILEMENT UNE COMPANY
	 */
	public void removeCompany(int id) {
		if (id > this.companiesList.size())
			return;
		this.companiesList.remove(id - 1);

//		for (Company e : this.companiesList)
//			if (e.getId() == id) {
//				this.removeCompany(Optional.of(e));
//				return;
//			}
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
		return Objects.equals(companiesList, other.companiesList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(companiesList);
	}

	@Override
	public String toString() {
		return "Companies [companies=" + companiesList + "\n ]";
	}
}
