package fr.excilys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import fr.excilys.model.Company;
import fr.excilys.model.Computer;

@Component
public class ComputerRowMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setIntroduced(rs.getDate("introduced"));
		computer.setDiscontinued(rs.getDate("discontinued"));
		final String companyName = rs.getString("company_name");
		if (companyName != null) {
			Company company = new Company();
			company.setId(rs.getInt("company_id"));
			company.setName(companyName);
			computer.setCompany(company);
		}
		return computer;
	}

}
