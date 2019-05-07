package fr.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.excilys.model.Companies;
import fr.excilys.model.Company;

public class CompanyMapper {

	private static CompanyMapper instance = null;

	private CompanyMapper() {

	}

	public static CompanyMapper getInstance() {
		if (instance == null)
			instance = new CompanyMapper();
		return instance;
	}

	public void toBean(ResultSet rs, Companies companies) throws SQLException {
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		companies.addCompany(company);
	}
	
	public Company toBean(int id, String name) {
		Company company = new Company();
		company.setId(id);
		company.setName(name);
		return company;
	}
}
