package service;

import java.sql.SQLException;

import dao.ComputerDao;
import dao.DaoException;
import dao.DaoFactory;

public class ComputerService {

	public static void listComputers() {
		DaoFactory dc = DaoFactory.getInstance();
		ComputerDao cd = dc.getComputer();
		try {
			cd.read();
		} catch (DaoException | SQLException e) {
			e.printStackTrace();
		}
	}
}
