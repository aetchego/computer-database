package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import dao.CompanyDao;
import dao.DaoException;
import dao.DaoFactory;
import model.Companies;
import model.Company;

public class CompanyService {

	public static void listCompanies() {
		DaoFactory df = DaoFactory.getInstance();
		CompanyDao dc = df.getCompany();
		Optional<Companies> optional;
		ArrayList<Company> companies = new ArrayList<Company>();
		try {
			optional = dc.read();
			companies = (optional.get()).getCompanies();
			for (Company e : companies)
				System.out.println(e.toString());
		} catch (DaoException | SQLException e) {
			System.out.println("[ERROR] Ooops, something went wrong !");
		}
	}
}
