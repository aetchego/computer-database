package service;

import java.sql.Date;
import java.sql.SQLException;

import dao.ComputerDao;
import dao.DaoException;
import dao.DaoFactory;
import model.Computer;

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
	
	public static void createComputer(String name, Date inDate, Date outDate, String brand) {
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(inDate);
		computer.setDiscontinued(outDate);
		
		DaoFactory dc = DaoFactory.getInstance();
		ComputerDao cd = dc.getComputer();
		try {
			cd.create(computer);
		} catch (DaoException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteComputer(int id) {
		DaoFactory dc = DaoFactory.getInstance();
		ComputerDao cd = dc.getComputer();
		try {
			cd.delete(id);
		} catch (DaoException | SQLException e) {
		}
	}
}
