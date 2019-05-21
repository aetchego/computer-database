package fr.excilys.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.client.UserException;
import fr.excilys.model.Companies;
import fr.excilys.model.Company;

@Component
@Transactional(readOnly = true)
public class CompanyDao {

	private static final String SELECT = "SELECT * FROM company";
	private static final String DELETE = "DELETE FROM `computer-database-db`.company where(id) LIKE ?";
	private final DataSource dataSource;
	private Companies companies = null;
	private JdbcTemplate template;

	public CompanyDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(this.dataSource);
		this.companies = this.read();
	}

	public void deleteCompany(int id) throws UserException, SQLException {
		template.update(DELETE, id);
	}

	public Companies read() throws DataAccessException {
		if (this.companies == null) {
			this.companies = new Companies();
			this.companies.setCompaniesList(template.query(SELECT, new BeanPropertyRowMapper<Company>(Company.class)));
		}
		return this.companies;
	}

	public Optional<Company> searchCompany(List<Company> companies, int id) throws UserException {
		for (Company e : companies)
			if (e.getId() == id)
				return Optional.of(e);
		throw new UserException("[ERROR] Company ID does not exist.");
	}

	public Optional<Company> findByName(String name) throws SQLException {
		return this.read().getCompaniesList().stream().filter(c -> c.getName().equals(name)).findFirst();
	}
}
