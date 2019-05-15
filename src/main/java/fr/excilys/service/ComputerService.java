package fr.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.excilys.client.UserException;
import fr.excilys.dao.ComputerDao;
import fr.excilys.dao.DaoException;
import fr.excilys.model.Computer;

@Component
public class ComputerService {

	private final ComputerDao computerDao;
	private static final String DATABASE_ERROR = "[ERROR] Ooops, something went wrong !";
	private static final String ID_ERROR = "[ERROR] ID does not exist.";

	public ComputerService(ComputerDao computerDao) {
		super();
		this.computerDao = computerDao;
	}

	public List<Computer> listComputers(int offset, int limit) throws UserException {
		List<Computer> computers = new ArrayList<>();
		try {
			computers = computerDao.read(offset, limit);
		} catch (SQLException e) {
			throw new UserException(DATABASE_ERROR);
		}
		return computers;
	}

	public List<Computer> search(String name, String filter) throws UserException {
		List<Computer> computers = new ArrayList<>();
		try {
			computers = computerDao.search(name, filter);
		} catch (SQLException e) {
			throw new UserException(DATABASE_ERROR);
		}
		return computers;
	}

	public void createComputer(Computer computer) throws UserException {
		try {
			computerDao.create(computer);
		} catch (SQLException e) {
			throw new UserException(DATABASE_ERROR);
		}
	}

	public void deleteComputer(int id) throws UserException {
		try {
			computerDao.delete(id);

		} catch (SQLException e) {
			throw new UserException(ID_ERROR);
		}
	}

	public Computer showDetails(int id) throws UserException {
		try {
			return computerDao.showDetails(id);
		} catch (DaoException | SQLException e) {
			throw new UserException(ID_ERROR);
		}
	}

	public void updateComputer(int id, Computer computer) throws UserException {
		try {
			computerDao.update(id, computer);
		} catch (DaoException | SQLException e) {
			throw new UserException(ID_ERROR);
		}
	}

	public int countComputers() throws UserException {
		int res = 0;
		try {
			res = computerDao.countComputers();
		} catch (SQLException e) {
			throw new UserException(DATABASE_ERROR);
		}
		return res;
	}
}
