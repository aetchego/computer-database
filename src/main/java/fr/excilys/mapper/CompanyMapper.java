package fr.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import fr.excilys.model.Companies;
import fr.excilys.model.Company;

@Component
public class CompanyMapper {

	public Company toBean(int id, String name) {
		Company company = new Company();
		company.setId(id);
		company.setName(name);
		return company;
	}

	public void toBean(ResultSet rs, Companies companies) throws SQLException {
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		companies.addCompany(company);
	}
}
