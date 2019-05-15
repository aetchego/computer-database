package fr.excilys.service;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import fr.excilys.client.UserException;
import fr.excilys.dao.CompanyDao;
import fr.excilys.model.Companies;

@Component
public class CompanyService {

	private final CompanyDao companyDao;

	public CompanyService(CompanyDao companyDao) {
		super();
		this.companyDao = companyDao;
	}

	public Companies listCompanies() throws UserException {
		Companies companies;
		try {
			companies = companyDao.read();
		} catch (SQLException e) {
			throw new UserException("[ERROR] Ooops, something went wrong !");
		}
		return companies;
	}

	public void deleteCompany(int id) throws UserException {
		try {
			companyDao.deleteCompany(id);
		} catch (SQLException e) {
			throw new UserException("[ERROR] Ooops, something went wrong !");
		}
	}

}
