package fr.excilys.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import fr.excilys.model.Companies;
import fr.excilys.model.Company;
import fr.excilys.persistence.crud.CompanyRepository;
import fr.excilys.persistence.exception.DaoException;

@Component
public class CompanyDao {

	private Companies companies = null;
	private CompanyRepository repository;

	public CompanyDao(CompanyRepository repository) {
		super();
		this.repository = repository;
		this.companies = this.search();
	}

	public Companies delete(Integer id) throws DataAccessException {
		this.repository.deleteById(id);
		return this.companies;
	}

	public Companies search() throws DataAccessException {
		if (this.companies == null) {
			this.companies = new Companies();
			this.companies.setCompaniesList(this.repository.findAll());
		}
		return this.companies;
	}

	public Optional<Company> searchCompany(List<Company> companies, int id) throws DaoException {
		for (Company e : companies)
			if (e.getId() == id)
				return Optional.of(e);
		throw new DaoException("[ERROR] Company ID does not exist.");
	}

	public Optional<Company> findByName(String name) {
		return this.companies.getCompaniesList().stream().filter(c -> c.getName().equals(name)).findFirst();
	}
}
