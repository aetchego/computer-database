package fr.excilys.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.client.UserException;
import fr.excilys.dao.CompanyDao;
import fr.excilys.dao.ComputerDao;
import fr.excilys.model.Companies;
import fr.excilys.model.Company;

@Component
@Transactional(readOnly = true)
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
	public void deleteCompany(int id) throws UserException {
		try {
			computerDao.deleteByCompany(id);
			companyDao.deleteCompany(id);
		} catch (SQLException e) {
			throw new UserException(ERROR_MESSAGE);
		}
	}

	public Companies listCompanies() throws UserException {
		Companies companies;
		try {
			companies = companyDao.read();
		} catch (DataAccessException e) {
			throw new UserException(ERROR_MESSAGE);
		}
		return companies;
	}

	public Optional<Company> findByName(String name) throws UserException {
		try {
			return companyDao.findByName(name);
		} catch (SQLException e) {
			throw new UserException(ERROR_MESSAGE);
		}
	}
}
