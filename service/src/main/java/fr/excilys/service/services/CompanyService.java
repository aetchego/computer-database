package fr.excilys.service.services;

import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.binding.exception.UserException;
import fr.excilys.model.Companies;
import fr.excilys.model.Company;
import fr.excilys.persistence.dao.CompanyDao;
import fr.excilys.persistence.dao.ComputerDao;

@Component
public class CompanyService {

	private static final String ERROR_MESSAGE = "[ERROR] Ooops, something went wrong !";
	private final CompanyDao companyDao;
	private final ComputerDao computerDao;

	public CompanyService(CompanyDao companyDao, ComputerDao computerDao) {
		super();
		this.companyDao = companyDao;
		this.computerDao = computerDao;
	}

	@Transactional
	public void delete(int id) throws UserException {
		try {
			// computerDao.deleteByCompany(id);
			(companyDao.delete(id)).removeCompany(id);
		} catch (DataAccessException e) {
			throw new UserException(ERROR_MESSAGE);
		}
	}

	public Companies search() throws UserException {
		Companies companies;
		try {
			companies = companyDao.search();
		} catch (DataAccessException e) {
			throw new UserException(ERROR_MESSAGE);
		}
		return companies;
	}

	public Optional<Company> findByName(String name) {
		return companyDao.findByName(name);
	}
}
