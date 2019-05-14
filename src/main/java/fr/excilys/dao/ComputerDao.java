package fr.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.excilys.client.UserException;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.model.Computer;

public class ComputerDao {

	private DaoFactory factory;
	private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private static final String INSERT = "INSERT into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
	private static final String SELECT = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.name AS company_name, computer.company_id\n"
			+ "from company\n" + "RIGHT JOIN computer\n"
			+ "ON company.id = computer.company_id";
	private static final String DELETE = "DELETE FROM computer where(id) LIKE ?";
	private static final String DETAILS = SELECT + " WHERE(computer.id) LIKE ?";
	private static final String SEARCH_BY = SELECT + " WHERE(`computer-database-db`.";
	private static final String COUNT = "SELECT COUNT(*) AS rowcount FROM computer";
	private static ComputerDao instance = null;
	private final ComputerMapper computerMapper = ComputerMapper.getInstance();

	private ComputerDao(DaoFactory factory) {
		this.factory = factory;
	}

	public static ComputerDao getInstance(DaoFactory factory) {
		if (instance == null)
			instance = new ComputerDao(factory);
		return instance;
	}

	public void create(Computer computer) throws SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		try {
			Integer companyId = Objects.isNull(computer.getCompany()) ? null : computer.getCompany().getId();
			DaoUtilitaries.databaseAccess(sql, INSERT, this.factory, 1, computer.getName(), computer.getIntroduced(),
					computer.getDiscontinued(), companyId);
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement) sql.get(1),
					(Connection) sql.get(2));
		}
	}

	public List<Computer> read(int offset, int limit) throws DaoException, SQLException {
		Computer computer;
		ArrayList<Computer> computers = new ArrayList<>();
		ArrayList<Object> sql = new ArrayList<>();
		try {
			DaoUtilitaries.databaseAccess(sql, SELECT + " LIMIT ? OFFSET ?", this.factory, 0, limit, offset);
			while (((ResultSet) sql.get(0)).next()) {
				computer = computerMapper.dbToBean((ResultSet) sql.get(0));
				computers.add(computer);
			}
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement) sql.get(1),
					(Connection) sql.get(2));
		}
		return computers;
	}
	
	public List<Computer> search(String name, String filter) throws DaoException, SQLException {
		Computer computer;
		ArrayList<Computer> computers = new ArrayList<>();
		ArrayList<Object> sql = new ArrayList<>();
		try {
			DaoUtilitaries.databaseAccess(sql, SEARCH_BY  + filter + ") LIKE ?", this.factory, 0, name);
			while (((ResultSet) sql.get(0)).next()) {
				computer = computerMapper.dbToBean((ResultSet) sql.get(0));
				computers.add(computer);
			}
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement) sql.get(1),
					(Connection) sql.get(2));
		}
		return computers;
	}

	public void update(Integer id, Computer computer) throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		try {
			Integer companyId = Objects.isNull(computer.getCompany()) ? null : computer.getCompany().getId();
			DaoUtilitaries.databaseAccess(sql, UPDATE, this.factory, 1, computer.getName(), computer.getIntroduced(),
					computer.getDiscontinued(), companyId, id);
			if ((Integer) sql.get(3) == 0)
				throw new DaoException("[ERROR] No results have been found.");
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement) sql.get(1),
					(Connection) sql.get(2));
		}
	}

	public void delete(int id) throws SQLException, UserException {
		ArrayList<Object> sql = new ArrayList<>();
		try {
			DaoUtilitaries.databaseAccess(sql, DELETE, this.factory, 1, id);
			if ((Integer) sql.get(3) == 0)
				throw new UserException("[ERROR] ID does not exist.");
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement) sql.get(1),
					(Connection) sql.get(2));
		}
	}

	public Computer showDetails(int id) throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		Computer computer;
		try {
			DaoUtilitaries.databaseAccess(sql, DETAILS, this.factory, 0, id);
			if (!((ResultSet) sql.get(0)).next())
				throw new DaoException("[ERROR] No results have been found.");
			computer = computerMapper.dbToBean((ResultSet) sql.get(0));
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement) sql.get(1),
					(Connection) sql.get(2));
		}
		return computer;
	}
	
	public int countComputers() throws SQLException {
		int res = 0;
		ArrayList<Object> sql = new ArrayList<>();
		try {
			DaoUtilitaries.databaseAccess(sql, COUNT, this.factory, 0);
			((ResultSet) sql.get(0)).next();
			res = ((ResultSet) sql.get(0)).getInt("rowcount");

		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement) sql.get(1),
					(Connection) sql.get(2));
		}
		return res;
	}
}
