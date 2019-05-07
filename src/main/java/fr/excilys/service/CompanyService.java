package fr.excilys.service;

import java.sql.SQLException;

import fr.excilys.client.Display;
import fr.excilys.client.UserException;
import fr.excilys.dao.CompanyDao;
import fr.excilys.dao.DaoException;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Companies;
import fr.excilys.model.Company;

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
			System.out.println(companies.getCompanies());
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] Ooops, something went wrong !");
		}
		return companies;
	}
	
	public void deleteCompany(int id) throws UserException {
		try {
			dc.read();
			dc.deleteCompany(id);
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] Ooops, something went wrong !");
		}
	}
}
