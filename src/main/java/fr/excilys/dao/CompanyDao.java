package fr.excilys.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import fr.excilys.mapper.CompanyMapper;
import fr.excilys.model.Companies;

public class CompanyDao {

	private DaoFactory factory;
	private Companies companies = null;
	private static CompanyDao company = null;
	private CompanyMapper companyMapper = CompanyMapper.getInstance();
	private final String SELECT = "SELECT * FROM `computer-database-db`.company";
	
	private CompanyDao(DaoFactory factory) {
		this.factory = factory;
	}
	
	public static CompanyDao getInstance(DaoFactory factory) {
		if (company == null)
			company = new CompanyDao(factory);	
		return company;
	}
	
	public Optional<Companies> read() throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
			if (this.companies == null) {
			try {
				companies = new Companies();
				DaoUtilitaries.databaseAccess(sql, SELECT, this.factory, 0);
				while (((ResultSet) sql.get(0)).next())
					companyMapper.toBean((ResultSet) sql.get(0), companies);
			} finally {
				DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));	
			}
		}
		return Optional.ofNullable(companies);
	}
}
