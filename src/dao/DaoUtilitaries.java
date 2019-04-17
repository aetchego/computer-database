package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoUtilitaries {
	
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
	
	public static void closeConnexions(ResultSet rs, Statement st, Connection co) throws SQLException {
		DaoUtilitaries.closeConnexions(rs);
		DaoUtilitaries.closeConnexions(st, co);
	}
	
	public static ArrayList<Object> databaseAccess(String sql, DaoFactory factory, int update, Object ...objects) {
		Connection co = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<Object> con = new ArrayList<>();
		
		try {
			co = factory.getConnection();
			st = DaoUtilitaries.initPreparedRequest(co, sql, objects);
			if (update == 0)
				rs = st.executeQuery();
			else
				st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.add(rs);
			con.add(st);
			con.add(co);
		}
		return con;
	}
}
