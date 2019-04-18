package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Company;
import model.Computer;

public class ComputerDao implements DaoInterface {

	private final String UPDATE = "UPDATE `computer-database-db`.computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private DaoFactory factory;
	private final String INSERT = "INSERT into `computer-database-db`.computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
	private final String SELECT = "SELECT `computer-database-db`.computer.id, `computer-database-db`.computer.name, `computer-database-db`.computer.introduced, `computer-database-db`.computer.discontinued, `computer-database-db`.company.name AS company_name, `computer-database-db`.computer.company_id\n" + 
			"from `computer-database-db`.company\n" + 
			"RIGHT JOIN `computer-database-db`.computer\n" + 
			"ON `computer-database-db`.company.id = `computer-database-db`.computer.company_id";
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
			System.out.println("***************************************\nComputer has been added to database !\n***************************************\n");
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		}
	}

	@Override
	public void read() throws DaoException, SQLException {
		Computer computer = null;
		ArrayList<Object> sql = new ArrayList<>();

		try {
			DaoUtilitaries.databaseAccess(sql, SELECT, this.factory, 0);
			while (((ResultSet) sql.get(0)).next()) {
				computer = ComputerDao.toBean((ResultSet) sql.get(0));
				System.out.println(computer.toLittleString());
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] Oops, something went wrong.");
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		}
	}

	@Override
	public void update(Computer computer, Integer id) throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		try {
			DaoUtilitaries.databaseAccess(sql, UPDATE, this.factory, 1, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompanyId(), id);
			if ((Integer) sql.get(3) == 0)
				throw new DaoException();
			System.out.println("************************************\nComputer has been updated !\n************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[ERROR] Oops, something went wrong.");
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
			System.out.println("*******************************************\nComputer has been deleted from database !\n*******************************************\n");
		} catch (SQLException e) {
			System.out.println("[ERROR] Oops, something went wrong.");
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		}
	}
	
	public void showDetails(int id) throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		Computer computer = new Computer();
		
		try {
			DaoUtilitaries.databaseAccess(sql, DETAILS, this.factory, 0, id);
			if (!((ResultSet) sql.get(0)).next())
				throw new DaoException();
			computer = ComputerDao.toBean((ResultSet) sql.get(0));
			System.out.println(computer.toString());
		} catch (SQLException e) {
			System.out.println("[ERROR] Oops, something went wrong.");
		} finally {
			DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		}
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
