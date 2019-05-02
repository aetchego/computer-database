package fr.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.excilys.client.UserException;
import fr.excilys.dao.ComputerDao;
import fr.excilys.dao.DaoException;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Companies;
import fr.excilys.model.Computer;

public class ComputerService {

	private static ComputerService instance = null;
	private DaoFactory factory = DaoFactory.getInstance();
	private ComputerDao cd = factory.getComputer();

	private ComputerService() {

	}

	public static ComputerService getInstance() {
		if (instance == null)
			instance = new ComputerService();
		return instance;
	}

	public List<Computer> listComputers(int offset, int limit) throws UserException {
		List<Computer> computers = new ArrayList<>();
		try {
			computers = cd.read(offset, limit);
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] Ooops, something went wrong !");
		}
		return computers;
	}

	public void createComputer(Computer computer, String brand) throws UserException {
		try {
			Companies companies = factory.getCompany().read();
			computer.searchCompany(brand, companies);
			cd.create(computer);
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] Company does not exist.");
		}
	}

	public void deleteComputer(int id) throws UserException {
		try {
			cd.delete(id);
			
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] ID does not exist.");
		}
	}

	public void showDetails(int id) throws UserException {
		try {
			cd.showDetails(id);
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] ID does not exist.");
		}
	}

	public void updateComputer(Computer computer, String brand, int id) throws UserException {
		try {
			Companies companies = factory.getCompany().read();
			computer.searchCompany(brand, companies);
			cd.update(computer, id);	
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] ID does not exist.");
		}
	}
	
	public int countComputers() throws UserException {
		int res = 0;
		try {
			res = cd.countComputers();
		} catch (DaoException | SQLException e) {
			throw new UserException("[ERROR] Ooops, something went wrong !");
		}
		return res;
	}
}
