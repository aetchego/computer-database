package fr.excilys.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import fr.excilys.client.UserException;
import fr.excilys.dao.CompanyDao;
import fr.excilys.model.Companies;
import fr.excilys.model.Company;

@Component
public class CompanyService {

	private static final String ERROR_MESSAGE = "[ERROR] Ooops, something went wrong !";
	private final CompanyDao companyDao;

	public CompanyService(CompanyDao companyDao) {
		super();
		this.companyDao = companyDao;
	}

	public void deleteCompany(int id) throws UserException {
		try {
			companyDao.deleteCompany(id);
		} catch (SQLException e) {
			throw new UserException(ERROR_MESSAGE);
		}
	}

	public Companies listCompanies() throws UserException {
		Companies companies;
		try {
			companies = companyDao.read();
		} catch (SQLException e) {
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
