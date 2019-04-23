package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import model.Companies;
import model.Company;
import model.Computer;

public class CompanyDao {

	private DaoFactory factory;
	private Companies companies = null;
	private static CompanyDao company = null;
	private final String SELECT = "SELECT * FROM `computer-database-db`.company";
	
	private CompanyDao(DaoFactory factory) {
		this.factory = factory;
	}
	
	public Optional<Companies> read() throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		try {
			if (this.companies == null) {
				companies = new Companies();
				DaoUtilitaries.databaseAccess(sql, SELECT, this.factory, 0);
				while (((ResultSet) sql.get(0)).next())
					CompanyDao.toBean((ResultSet) sql.get(0), companies);
			}
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));	
		}
		return Optional.ofNullable(companies);
	}
	
	public static CompanyDao getInstance(DaoFactory factory) {
		if (company == null)
			company = new CompanyDao(factory);	
		return company;
	}
	
	private static void toBean(ResultSet rs, Companies companies) throws SQLException {
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		companies.addCompany(company);
	}
}
