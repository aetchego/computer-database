package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Computer;

public class DaoUtilitaries {

/*	public static PreparedStatement initPreparedRequest(Connection co, String sql, Object ...objects) {
		PreparedStatement st = null;
		try {
			st = co.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				st.setObject(i + 1, objects[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}*/
	
	public static PreparedStatement initPreparedRequest(Connection co, String sql, Object ...objects) throws SQLException {
		PreparedStatement st = co.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				st.setObject(i + 1, objects[i]);
			}
		return st;
	}
	
	public static void closeConnexions(Statement st, Connection co) throws SQLException {
		if (st != null)
			st.close();
		if (co != null)
			co.close();
	}
	
	public static void closeConnexions(ResultSet rs) throws SQLException {
		if (rs != null)	
			rs.close();
	}
}
