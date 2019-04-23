package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import model.Company;
import model.Computer;

public class ComputerDao implements DaoInterface {

	private final String UPDATE = "UPDATE `computer-database-db`.computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private DaoFactory factory;
	private final String INSERT = "INSERT into `computer-database-db`.computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
	private final String SELECT = "SELECT `computer-database-db`.computer.id, `computer-database-db`.computer.name, `computer-database-db`.computer.introduced, `computer-database-db`.computer.discontinued, `computer-database-db`.company.name AS company_name, `computer-database-db`.computer.company_id\n" + 
			"from `computer-database-db`.company\n" + 
			"RIGHT JOIN `computer-database-db`.computer\n" + 
			"ON `computer-database-db`.company.id = `computer-database-db`.computer.company_id LIMIT ? OFFSET ?";
	private final String DELETE = "DELETE FROM `computer-database-db`.computer where(id) LIKE ?";
	private final String DETAILS = SELECT + " WHERE(`computer-database-db`.computer.id) LIKE ?";
	
	ComputerDao(DaoFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(Computer computer) throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		try {
			DaoUtilitaries.databaseAccess(sql, INSERT, this.factory, 1, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompanyId());
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		}
	}

	@Override
	public Optional<ArrayList<Computer>> read(int offset, int limit) throws DaoException, SQLException {
		Computer computer;
		ArrayList<Computer> computers = new ArrayList<>();
		ArrayList<Object> sql = new ArrayList<>();
		try {
			DaoUtilitaries.databaseAccess(sql, SELECT, this.factory, 0, limit, offset);
			while (((ResultSet) sql.get(0)).next()) {
				computer = ComputerDao.toBean((ResultSet) sql.get(0));
				computers.add(computer);
			}
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		}
		return Optional.ofNullable(computers);
	}

	@Override
	public void update(Computer computer, Integer id) throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		try {
			DaoUtilitaries.databaseAccess(sql, UPDATE, this.factory, 1, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompanyId(), id);
			if ((Integer) sql.get(3) == 0)
				throw new DaoException();
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		}
	}

	@Override
	public void delete(int id) throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		try {
			DaoUtilitaries.databaseAccess(sql, DELETE, this.factory, 1, id);
			if ((Integer) sql.get(3) == 0)
				throw new DaoException();
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		}
	}
	
	public Optional<Computer> showDetails(int id) throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		Computer computer;
		
		try {
			DaoUtilitaries.databaseAccess(sql, DETAILS, this.factory, 0, id);
			if (!((ResultSet) sql.get(0)).next())
				throw new DaoException();
			computer = ComputerDao.toBean((ResultSet) sql.get(0));
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		}
		return Optional.ofNullable(computer);
	}

	private static Computer toBean(ResultSet rs) throws SQLException {
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setBrand(rs.getString("company_name"));
		computer.setCompanyId(rs.getInt("company_id"));
		computer.setIntroduced(rs.getDate("introduced"));
		computer.setDiscontinued(rs.getDate("discontinued"));
		return computer;
	}
}
