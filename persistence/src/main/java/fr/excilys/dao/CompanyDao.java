package fr.excilys.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import fr.excilys.exception.UserException;
import fr.excilys.model.Companies;
import fr.excilys.model.Company;

@Component
public class CompanyDao {

	private static final String SELECT = "SELECT * FROM company";
	private static final String DELETE = "DELETE FROM `computer-database-db`.company where(id) LIKE ?";
	private Companies companies = null;
	private JdbcTemplate template;

	public CompanyDao(JdbcTemplate template) {
		super();
		this.template = template;
		this.companies = this.search();
	}

	public Companies delete(int id) throws DataAccessException {
		template.update(DELETE, id);
		return this.companies;
	}

	public Companies search() throws DataAccessException {
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

	public Optional<Company> findByName(String name) {
		return this.companies.getCompaniesList().stream().filter(c -> c.getName().equals(name)).findFirst();
	}
}
