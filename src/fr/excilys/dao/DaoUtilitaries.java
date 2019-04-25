package fr.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoUtilitaries {
	
	public static PreparedStatement initPreparedRequest(Connection co, String sql, Object ...objects) throws SQLException {
		PreparedStatement st = co.prepareStatement(sql);
		System.out.println(objects.length);
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
	
	public static void databaseAccess(ArrayList<Object> infos, String sql, DaoFactory factory, int update, Object ...objects) throws SQLException {
		Connection co = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			co = factory.getConnection();
			st = DaoUtilitaries.initPreparedRequest(co, sql, objects);
			if (update == 0)
				rs = st.executeQuery();
			else
				count = st.executeUpdate();
		} finally {
			infos.add(rs);
			infos.add(st);
			infos.add(co);
			infos.add(count);
		}
	}
}
