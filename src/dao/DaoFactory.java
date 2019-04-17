package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {

	private static final String PROP_FILE = "dao/dao.properties";
	private static final String PROP_URL = "url";
	private static final String PROP_USR = "user";
	private static final String PROP_PWD = "pwd";
	
	private String url;
	private String usr;
	private String pwd;
	
	DaoFactory(String url, String usr, String pwd) {
		this.url = url;
		this.usr = usr;
		this.pwd = pwd;
	}
	
	public static DaoFactory getInstance() throws DaoConfigException {
		String url;
		String usr;
		String pwd;
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fileProp = classLoader.getResourceAsStream(PROP_FILE);
		
		if (fileProp == null)
			throw new DaoConfigException("Impossible to find properties file.");
		try {
			properties.load(fileProp);
			url = properties.getProperty(PROP_URL);
			usr = properties.getProperty(PROP_USR);
			pwd = properties.getProperty(PROP_PWD);
		} catch (Exception e) {
			throw new DaoConfigException("Impossible to load properties file.");
		}

		DaoFactory instance = new DaoFactory(url, usr, pwd);
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, usr, pwd);
	}
	
	public ComputerDao getComputer() {
	
		return new ComputerDao(this);
	}
	
	public CompanyDao getCompany() {
		return CompanyDao.getInstance(this);
	}
}
