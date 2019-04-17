package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Company;
import model.Computer;

public class ComputerDao implements DaoInterface {

	private DaoFactory factory;
	
	ComputerDao(DaoFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(Computer computer) throws DaoException, SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		sql = DaoUtilitaries.databaseAccess("insert into `computer-database-db`.computer(name, introduced, discontinued) values (?, ?, ?)", 
		this.factory, 1, computer.getName(), computer.getIntroduced(), computer.getDiscontinued());
		DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
	}

	@Override
	public void read() throws DaoException, SQLException {
		Computer computer = null;
		ArrayList<Object> sql = new ArrayList<>();

		sql = DaoUtilitaries.databaseAccess("SELECT * FROM `computer-database-db`.computer", this.factory, 0);
		while (((ResultSet) sql.get(0)).next()) {
			computer = ComputerDao.toBean((ResultSet) sql.get(0));
			System.out.println(computer.toString());
		}
		DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
	}

	@Override
	public void update() throws DaoException {
		// TODO Auto-generated method stub
		
	}

	public boolean doesExist(int id) throws SQLException {
		ArrayList<Object> sql = new ArrayList<>();
		boolean exists = true;

		sql = DaoUtilitaries.databaseAccess("SELECT * FROM `computer-database-db`.computer where(id) LIKE ?", this.factory, 0, id);
		if (!((ResultSet) sql.get(0)).next())
			exists = false;
		DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		return exists;
	}
	@Override
	public void delete(int id) throws DaoException, SQLException {
		try {
			if (!this.doesExist(id)) {throw new DaoException("[ERROR] ID cannot be found in database.");}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<Object> sql = new ArrayList<>();
		sql = DaoUtilitaries.databaseAccess("DELETE FROM `computer-database-db`.computer where(id) LIKE ?", this.factory, 1, id);
		DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
	}

	private static Computer toBean(ResultSet rs) throws SQLException {
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setCompany_id(rs.getInt("company_id"));
		computer.setIntroduced(rs.getDate("introduced"));
		computer.setDiscontinued(rs.getDate("discontinued"));
		return computer;
	}
}
