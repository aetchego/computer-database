package fr.excilys.service;

import java.sql.SQLException;

import fr.excilys.client.UserException;
import fr.excilys.dao.CompanyDao;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Companies;

public class CompanyService {

	private DaoFactory df = DaoFactory.getInstance();
	private CompanyDao dc = df.getCompany();
	private static CompanyService instance = null;

	private CompanyService() {
	}

	public static CompanyService getInstance() {
		if (instance == null)
			instance = new CompanyService();
		return instance;
	}

	public Companies listCompanies() throws UserException {
		Companies companies;
		try {
			companies = dc.read();
		} catch (SQLException e) {
			throw new UserException("[ERROR] Ooops, something went wrong !");
		}
		return companies;
	}
	
	public void deleteCompany(int id) throws UserException {
		try {
			dc.read();
			dc.deleteCompany(id);
		} catch (SQLException e) {
			throw new UserException("[ERROR] Ooops, something went wrong !");
		}
	}
}
