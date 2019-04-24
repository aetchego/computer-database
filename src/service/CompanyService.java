package service;

import java.sql.SQLException;
import java.util.Optional;
import client.Display;
import client.UserException;
import dao.CompanyDao;
import dao.DaoException;
import dao.DaoFactory;
import model.Companies;
import model.Company;

public class CompanyService {

	private DaoFactory df = DaoFactory.getInstance();
	private CompanyDao dc = df.getCompany();
	private Display<Company> display = new Display<>();
	private static CompanyService instance = null;
	
	private CompanyService() {
	}
	
	public static CompanyService getInstance() {
		if (instance == null)
			instance = new CompanyService();
		return instance;
	}
	
	public void listCompanies() {
		Optional<Companies> optional;
		try {
			optional = dc.read();
			display.displayFull(optional.get().getCompanies());
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] Ooops, something went wrong !");
		}
	}
}
