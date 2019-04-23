package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import dao.ComputerDao;
import dao.DaoException;
import dao.DaoFactory;
import model.Computer;

public class ComputerService {

	public static void listComputers(int offset, int limit) {
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		Optional <ArrayList<Computer>> optional;
		ArrayList<Computer> computers = new ArrayList<>();
		try {
			optional = cd.read(offset, limit);
			computers = optional.get();
			for (Computer e : computers)
				System.out.println(e.toLittleString());
		} catch (DaoException | SQLException e) {
			System.out.println("[ERROR] Ooops, something went wrong !");
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
		System.out.println("***************************************\nComputer has been added to database !\n***************************************\n");
		} catch (DaoException | SQLException e) {
			System.out.println("[ERROR] Company's ID does not exist.");
		}
	}
	
	public static void deleteComputer(int id) {
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			cd.delete(id);
			System.out.println("*******************************************\nComputer has been deleted from database !\n*******************************************\n");
		} catch (DaoException | SQLException e) {
			System.out.println("[ERROR] ID does not exist.");
		}
	}
	
	public static void showDetails(int id) {
		Optional<Computer> optional;
		Computer computer;
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			optional = cd.showDetails(id);
			computer = optional.get();
			System.out.println(computer.toString());
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
			System.out.println("************************************\nComputer has been updated !\n************************************\n");
		} catch (DaoException | SQLException e) {
			System.out.println("[ERROR] ID does not exist.");
		}
	}
}
