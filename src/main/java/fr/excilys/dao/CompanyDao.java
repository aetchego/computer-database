package fr.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import fr.excilys.client.UserException;
import fr.excilys.mapper.CompanyMapper;
import fr.excilys.model.Companies;
import fr.excilys.model.Company;

@Component
public class CompanyDao {

	private final DataSource dataSource;
	private Companies companies = null;
	private final CompanyMapper companyMapper;
	private static final String SELECT = "SELECT * FROM company";
	private static final String DELETE = "DELETE FROM `computer-database-db`.company where(id) LIKE ?";
	private static final String DELETE_COMPUTERS = "DELETE FROM `computer-database-db`.computer where(company_id) = ?";

	public CompanyDao(DataSource dataSource, CompanyMapper companyMapper) {
		super();
		this.dataSource = dataSource;
		this.companyMapper = companyMapper;
	}

	public Companies read() throws SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		if (this.companies == null) {
			try {
				companies = new Companies();
				DaoUtilitaries.databaseAccess(sql, SELECT, this.dataSource, 0);
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
		this.read();
		ArrayList<Object> sql = new ArrayList<>();
		try {
			DaoUtilitaries.databaseAccess(sql, DELETE_COMPUTERS, this.dataSource, 1, id);
			DaoUtilitaries.databaseAccess(sql, DELETE, this.dataSource, 1, id);
			companies.removeCompany(searchCompany(companies.getCompanies(), id));
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement) sql.get(1),
					(Connection) sql.get(2));
		}
	}
}
