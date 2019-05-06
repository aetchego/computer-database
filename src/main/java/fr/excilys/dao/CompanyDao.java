package fr.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.excilys.client.UserException;
import fr.excilys.mapper.CompanyMapper;
import fr.excilys.model.Companies;
import fr.excilys.model.Company;

public class CompanyDao {

	private DaoFactory factory;
	private Companies companies = null;
	private static CompanyDao company = null;
	private CompanyMapper companyMapper = CompanyMapper.getInstance();
	private final String SELECT = "SELECT * FROM `computer-database-db`.company";
	private final String DELETE = "DELETE FROM `computer-database-db`.company where(id) LIKE ?";
	private final String DELETE_COMPUTERS = "DELETE FROM `computer-database-db`.computer where(company_id) = ?";

	private CompanyDao(DaoFactory factory) {
		this.factory = factory;
	}

	public static CompanyDao getInstance(DaoFactory factory) {
		if (company == null)
			company = new CompanyDao(factory);
		return company;
	}

	public Companies read() throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		if (this.companies == null) {
			try {
				companies = new Companies();
				DaoUtilitaries.databaseAccess(sql, SELECT, this.factory, 0);
				while (((ResultSet) sql.get(0)).next())
					companyMapper.toBean((ResultSet) sql.get(0), companies);
			} finally {
				DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement) sql.get(1),
						(Connection) sql.get(2));
			}
		}
		return companies;
	}
	
	public Optional<Company> searchCompany(List<Company> companies, int id) throws UserException {
		for (Company e : companies)
			if (e.getId() == id)
				return Optional.of(e);
		throw new UserException("[ERROR] Company ID does not exist.");	
	}
	
	public void deleteCompany(int id) throws UserException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		try {
			//this.factory.getConnection().setAutoCommit(false);
			DaoUtilitaries.databaseAccess(sql, DELETE_COMPUTERS, this.factory, 1, id);
			DaoUtilitaries.databaseAccess(sql, DELETE, this.factory, 1, id);
			//this.factory.getConnection().commit();
			companies.remove(searchCompany(companies.getCompanies(), id));
		} /*catch (SQLException e) {
			this.factory.getConnection().rollback();
		}*/
		finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement) sql.get(1),
					(Connection) sql.get(2));
		}
	}
}
