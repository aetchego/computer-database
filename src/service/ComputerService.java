package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import client.Display;
import client.UserException;
import dao.ComputerDao;
import dao.DaoException;
import dao.DaoFactory;
import model.Computer;

public class ComputerService {

	private static ComputerService instance = null;
	private Display<Computer> display = new Display<>();
	
	private ComputerService() {
		
	}
	
	public static ComputerService getInstance() {
		if (instance == null)
			instance = new ComputerService();
		return instance;
	}
	public void listComputers(int offset, int limit) {
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		List<Computer> computers = new ArrayList<>();
		
		try {
			computers = cd.read(offset, limit);
			display.displayFull(computers);
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] Ooops, something went wrong !");
		}
	}
	
	public void createComputer(String name, Date inDate, Date outDate, Integer brand) {
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(inDate);
		computer.setDiscontinued(outDate);
		computer.setCompanyId(brand);
		
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
		cd.create(computer);
		display.printMsg("***************************************\nComputer has been added to database !\n***************************************\n");
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] Company's ID does not exist.");
		}
	}
	
	public void deleteComputer(int id) {
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			cd.delete(id);
			display.printMsg("*******************************************\nComputer has been deleted from database !\n*******************************************\n");
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] ID does not exist.");
		}
	}
	
	public void showDetails(int id) {
		Optional<Computer> optional;
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			optional = cd.showDetails(id);
			display.displayElement(optional.get());
		} catch (DaoException | SQLException e) {
			//e.printStackTrace();
			System.out.println("[ERROR] ID does not exist.");
			//throw new UserException("[ERROR] ID does not exist.");
		}
	}

	public void updateComputer(String name, Date dateIn, Date dateOut, Integer brand, int id) {
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(dateIn);
		computer.setDiscontinued(dateOut);
		computer.setCompanyId(brand);
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			cd.update(computer, id);
			display.printMsg("************************************\nComputer has been updated !\n************************************\n");
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] ID does not exist.");
		}
	}
}
