package fr.excilys.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.excilys.client.Display;
import fr.excilys.client.UserException;
import fr.excilys.dao.ComputerDao;
import fr.excilys.dao.DaoException;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Computer;

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
	public void listComputers(int offset, int limit) throws UserException {
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
	
	public void createComputer(String name, Date inDate, Date outDate, Integer brand) throws UserException {
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
	
	public void deleteComputer(int id) throws UserException {
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			cd.delete(id);
			display.printMsg("*******************************************\nComputer has been deleted from database !\n*******************************************\n");
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] ID does not exist.");
		}
	}
	
	public void showDetails(int id) throws UserException {
		Optional<Computer> optional;
		DaoFactory factory = DaoFactory.getInstance();
		ComputerDao cd = factory.getComputer();
		try {
			optional = cd.showDetails(id);
			display.displayElement(optional.get());
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] ID does not exist.");
		}
	}

	public void updateComputer(String name, Date dateIn, Date dateOut, Integer brand, int id) throws UserException {
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
