package service;

import java.sql.Date;
import java.sql.SQLException;

import dao.CompanyDao;
import dao.DaoException;
import dao.DaoFactory;

public class CompanyService {

	public static void listCompanies() {
		DaoFactory df = DaoFactory.getInstance();
		CompanyDao dc = df.getCompany();
		try {
			dc.read();
		} catch (DaoException | SQLException e) {
			e.getMessage();
		}
	}
}
