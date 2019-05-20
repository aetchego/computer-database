package fr.excilys.dao;

import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import fr.excilys.model.Computer;

@Component
public class ComputerDao {

	private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private static final String INSERT = "INSERT into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM computer where(id) LIKE ?";
	private static final String DETAILS = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.name AS company_name, computer.company_id\\n\"\n"
			+ "			+ \"from company\\n\" + \"RIGHT JOIN computer\\n\" + \"ON company.id = computer.company_id WHERE(computer.id) LIKE ?";
	private static final String COUNT = "SELECT COUNT(*) AS rowcount FROM computer";
	private static final String COUNT_FILTERED = "SELECT COUNT(*) AS rowcount FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE "
			+ " computer.name LIKE ? OR company.name LIKE ?";
	private final DataSource dataSource;
	private JdbcTemplate template;

	public ComputerDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(this.dataSource);
	}

	public int countComputers(String name) throws DataAccessException {

		if (name != null && !name.isEmpty())
			return template.queryForObject(COUNT_FILTERED, Integer.class, name, name);
		return template.queryForObject(COUNT, Integer.class);
	}

	public void create(Computer computer) throws DataAccessException {

		Integer companyId = Objects.isNull(computer.getCompany()) ? null : computer.getCompany().getId();
		template.update(INSERT, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), companyId);
	}

	public void delete(int id) throws DataAccessException {

		template.update(DELETE, id);
	}

	public List<Computer> search(String name, int offset, int limit, String query) throws DataAccessException {

		return name != null && !name.isEmpty()
				? template.query(query, new BeanPropertyRowMapper<Computer>(Computer.class), name, name, limit, offset)
				: template.query(query, new BeanPropertyRowMapper<Computer>(Computer.class), limit, offset);
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
