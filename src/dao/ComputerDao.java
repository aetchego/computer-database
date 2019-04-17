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
	public void create() throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read() throws DaoException, SQLException {
		Computer computer = null;
		ArrayList<Object> sql = new ArrayList<>();

		sql = DaoUtilitaries.databaseAccess("SELECT * FROM `computer-database-db`.computer", this.factory);
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

	@Override
	public void delete() throws DaoException {
		// TODO Auto-generated method stub
		
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
