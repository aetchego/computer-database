package fr.excilys.services.services;

import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.model.Companies;
import fr.excilys.model.Company;
import fr.excilys.persistence.crud.CompanyRepository;
import fr.excilys.persistence.crud.ComputerRepository;
import fr.excilys.services.exception.UserException;

@Component
public class CompanyService {

	private Companies companies = null;
	private final CompanyRepository companyRepository;
	private final ComputerRepository computerRepository;

	public CompanyService(CompanyRepository companyRepository, ComputerRepository computerRepository) {
		super();
		this.companyRepository = companyRepository;
		this.computerRepository = computerRepository;
	}

	@Transactional
	public void delete(int id) throws UserException {
		try {
			computerRepository.deleteByCompanyId(id);
			companyRepository.deleteById(id);
			companies.delete(id);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}

	public Companies search() throws UserException {
		try {
			if (this.companies == null) {
				this.companies = new Companies();
				this.companies.setCompaniesMap(this.companyRepository.findAll());
			}
			return this.companies;
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}

	public Optional<Company> findByName(String name) {
		return this.companies.getCompaniesList().stream().filter(c -> c.getName().equals(name)).findFirst();
	}
}
