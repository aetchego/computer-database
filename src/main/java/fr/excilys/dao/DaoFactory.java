package fr.excilys.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {

	private static final String PROP_FILE = "dao.properties";
	private static final String PROP_URL = "url";
	private static final String PROP_USR = "user";
	private static final String PROP_PWD = "pwd";
	private static final String PROP_DRIVER = "driver";
	
	private String url;
	private String usr;
	private String pwd;
	private String driver;
	
	DaoFactory(String url, String usr, String pwd, String driver) {
		this.url = url;
		this.usr = usr;
		this.pwd = pwd;
		this.driver = driver;
	}
	
	public static DaoFactory getInstance() throws DaoConfigException {
		String url;
		String usr;
		String pwd;
		String driver;
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
			driver = properties.getProperty(PROP_DRIVER);
			System.out.println("ok file");
		} catch (Exception e) {
			System.out.println("properties");
			throw new DaoConfigException("Impossible to load properties file.");
		}

		try {
            Class.forName(driver);
        } catch ( ClassNotFoundException e ) {
            throw new DaoConfigException("Cannot find driver.");
        }
		DaoFactory instance = new DaoFactory(url, usr, pwd, driver);
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, usr, pwd);
	}
	
	public ComputerDao getComputer() {
	
		return ComputerDao.getInstance(this);
	}
	
	public CompanyDao getCompany() {
		return CompanyDao.getInstance(this);
	}
}
