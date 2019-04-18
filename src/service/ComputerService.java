package service;

import java.sql.Date;
import java.sql.SQLException;

import dao.ComputerDao;
import dao.DaoException;
import dao.DaoFactory;
import model.Computer;

public class ComputerService {

	public static void listComputers() {
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			cd.read();
		} catch (DaoException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createComputer(String name, Date inDate, Date outDate, Integer brand) {
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(inDate);
		computer.setDiscontinued(outDate);
		computer.setCompanyId(brand);
		
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			cd.create(computer);
		} catch (DaoException | SQLException e) {
			System.out.println("[ERROR] Company's ID does not exist.");
		}
	}
	
	public static void deleteComputer(int id) {
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			cd.delete(id);
		} catch (DaoException | SQLException e) {
			System.out.println("[ERROR] ID does not exist.");
		}
	}
	
	public static void showDetails(int id) {
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			cd.showDetails(id);
		} catch (DaoException | SQLException e) {
			System.out.println("[ERROR] ID does not exist.");
		}
	}

	public static void updateComputer(String name, Date dateIn, Date dateOut, Integer brand, int id) {
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(dateIn);
		computer.setDiscontinued(dateOut);
		computer.setCompanyId(brand);
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			cd.update(computer, id);
		} catch (DaoException | SQLException e) {
			System.out.println("[ERROR] ID does not exist.");
		}
	}
}
