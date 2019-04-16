package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ComputerDao;
import dao.DaoFactory;

public class Service {

	public static void listComputers() {
		DaoFactory dc = DaoFactory.getInstance();
		ComputerDao cd = dc.getComputer();
		cd.read();
	}
}
