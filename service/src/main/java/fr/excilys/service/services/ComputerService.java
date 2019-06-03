package fr.excilys.service.services;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import fr.excilys.binding.exception.UserException;
import fr.excilys.model.Computer;
import fr.excilys.persistence.dao.ComputerDao;

@Component
public class ComputerService {

	private final ComputerDao computerDao;

	public ComputerService(ComputerDao computerDao) {
		super();
		this.computerDao = computerDao;
	}

	public int count(String name) throws UserException {
		try {
			return computerDao.count(name);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
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

	public Page<Computer> search(Pageable pageable, String name) throws UserException {
		try {
			if (Objects.isNull(name) || name.trim().isEmpty())
				return computerDao.search(pageable);
			return computerDao.findByName(pageable, name);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}

	public Computer findById(int id) throws UserException {
		try {
			return computerDao.findById(id);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}

	public void update(Computer computer) throws UserException {
		try {
			computerDao.add(computer);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}
}
