package fr.excilys.service.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import fr.excilys.binding.exception.UserException;
import fr.excilys.model.Computer;
import fr.excilys.persistence.dao.ComputerDao;

@Component
public class ComputerService {

	private static final String DATABASE_ERROR = "[ERROR] Ooops, something went wrong !";
	private static final String ID_ERROR = "[ERROR] ID does not exist.";
	private final ComputerDao computerDao;

	public ComputerService(ComputerDao computerDao) {
		super();
		this.computerDao = computerDao;
	}

	public int count(String name) throws UserException {
		try {
			return computerDao.count(name);
		} catch (DataAccessException e) {
			throw new UserException(DATABASE_ERROR);
		}
	}

	public void add(Computer computer) throws UserException {
		try {
			computerDao.add(computer);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}

	@Transactional
	public void delete(int id) throws UserException {
		try {
			computerDao.delete(id);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}

	public List<Computer> search(String name, int offset, int limit, String query) throws UserException {
		try {
			return computerDao.search(name, offset, limit, query);
		} catch (DataAccessException e) {
			throw new UserException(DATABASE_ERROR);
		}
	}

	public Computer showDetails(int id) throws UserException {
		try {
			return computerDao.showDetails(id);
		} catch (DataAccessException e) {
			throw new UserException(ID_ERROR);
		}
	}

	public void update(int id, Computer computer) throws UserException {
		try {
			System.out.println(computer.getId());
			// computerDao.update(id, computer);
			computerDao.add(computer);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}
}
