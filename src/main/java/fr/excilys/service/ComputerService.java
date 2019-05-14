package fr.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.excilys.client.UserException;
import fr.excilys.dao.ComputerDao;
import fr.excilys.dao.DaoException;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Computer;

public class ComputerService {

	private static ComputerService instance = null;
	private DaoFactory factory = DaoFactory.getInstance();
	private ComputerDao cd = factory.getComputer();
	private static final String DATABASE_ERROR = "[ERROR] Ooops, something went wrong !";
	private static final String ID_ERROR = "[ERROR] ID does not exist.";

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
			throw new UserException(DATABASE_ERROR); }
		return computers;
	}
	
	public List<Computer> search(String name, String filter) throws UserException {
		List<Computer> computers = new ArrayList<>();
		try {
			computers = cd.search(name, filter);
		} catch (DaoException | SQLException e) {
			throw new UserException(DATABASE_ERROR);
		}
		return computers;
	}

	public void createComputer(Computer computer) throws UserException {
		try {
			cd.create(computer);
		} catch (DaoException | SQLException e) {
			throw new UserException(DATABASE_ERROR);
		}
	}

	public void deleteComputer(int id) throws UserException {
		try {
			cd.delete(id);
			
		} catch (DaoException | SQLException e) {
			throw new UserException(ID_ERROR);
		}
	}

	public Computer showDetails(int id) throws UserException {
		try {
			return cd.showDetails(id);
		} catch (DaoException | SQLException e) {
			throw new UserException(ID_ERROR);
		}
	}

	public void updateComputer(int id, Computer computer) throws UserException {
		try {
			cd.update(id, computer);
		} catch (DaoException | SQLException e) {
			throw new UserException(ID_ERROR);
		}
	}
	
	public int countComputers() throws UserException {
		int res = 0;
		try {
			res = cd.countComputers();
		} catch (DaoException | SQLException e) {
			throw new UserException(DATABASE_ERROR);
		}
		return res;
	}
}
