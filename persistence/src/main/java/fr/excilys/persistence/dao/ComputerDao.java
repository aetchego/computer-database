package fr.excilys.persistence.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import fr.excilys.model.Computer;
import fr.excilys.persistence.crud.ComputerRepository;

@Component
public class ComputerDao {

	private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private static final String DETAILS = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.name AS company_name, computer.company_id\\n\"\n"
			+ "			+ \"from company\\n\" + \"RIGHT JOIN computer\\n\" + \"ON company.id = computer.company_id WHERE(computer.id) LIKE ?";
	private static final String COUNT = "SELECT COUNT(*) AS rowcount FROM computer";
	private static final String COUNT_FILTERED = "SELECT COUNT(*) AS rowcount FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE "
			+ " computer.name LIKE ? OR company.name LIKE ?";
	private static final String DELETE_COMPUTERS_BY_COMPANY = "DELETE FROM `computer-database-db`.computer where(company_id) = ?";

	private final JdbcTemplate template;
	private final ComputerRepository repository;

	public ComputerDao(JdbcTemplate template, ComputerRepository repository) {
		super();
		this.template = template;
		this.repository = repository;
	}

	public int count(String name) throws DataAccessException {

		if (name != null && !name.isEmpty())
			return template.queryForObject(COUNT_FILTERED, Integer.class, name, name);
		return template.queryForObject(COUNT, Integer.class);
	}

	public void add(Computer computer) throws DataAccessException {
		repository.save(computer);
	}

	public void delete(int id) throws DataAccessException {
		repository.deleteById(id);
	}

	public void deleteByCompany(int id) throws DataAccessException {

		template.update(DELETE_COMPUTERS_BY_COMPANY, id);
	}

	public List<Computer> search(String name, int offset, int limit, String query) throws DataAccessException {

		/*
		 * return name != null && !name.isEmpty() ? template.query(query,
		 * this.rowMapper, name, name, limit, offset) : template.query(query,
		 * this.rowMapper, limit, offset);
		 */
		return repository.findAll();
	}

	public Computer showDetails(int id) throws DataAccessException {

		return template.queryForObject(DETAILS, Computer.class, id);
	}

	public void update(Integer id, Computer computer) throws DataAccessException {

		Integer companyId = Objects.isNull(computer.getCompany()) ? null : computer.getCompany().getId();
		template.update(UPDATE, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), companyId,
				id);
	}
}
