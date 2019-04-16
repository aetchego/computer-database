package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public void read() throws DaoException {
		Connection co = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Computer computer = null;
		
		try {
			co = factory.getConnection();
			st = DaoUtilitaries.initPreparedRequest(co, "SELECT * FROM `computer-database-db`.computer");
			rs = st.executeQuery();
			while (rs.next()) {
				computer = ComputerDao.toBean(rs);
				System.out.println(computer.toString());
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DaoUtilitaries.closeConnexions(rs);
				DaoUtilitaries.closeConnexions(st, co);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
