package fr.excilys.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DaoFactory {

	private static DaoFactory instance;
	
	private HikariDataSource dataSource;

	private DaoFactory() {
		 String configFile = "/hikari.properties";
	        
	        HikariConfig cfg = new HikariConfig(configFile);
	        dataSource = new HikariDataSource(cfg);
	}

	public static DaoFactory getInstance() {
		if (instance == null) {
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			instance = new DaoFactory();
		}
		return instance;
	}
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public ComputerDao getComputer() {

		return ComputerDao.getInstance(this);
	}

	public CompanyDao getCompany() {
		return CompanyDao.getInstance(this);
	}
}
