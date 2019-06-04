package fr.excilys.services.services;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import fr.excilys.model.Computer;
import fr.excilys.persistence.crud.ComputerRepository;
import fr.excilys.services.exception.UserException;

@Component
public class ComputerService {

	private final ComputerRepository repository;

	public ComputerService(ComputerRepository repository) {
		super();
		this.repository = repository;
	}

	public int count(String name) throws UserException {
		try {
			if (name != null && !name.isEmpty())
				return repository.countByNameOrCompanyName(name, name);
			return ((Long) repository.count()).intValue();
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}

	public void save(Computer computer) throws UserException {
		try {
			repository.save(computer);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}

	@Transactional
	public void delete(int id) throws UserException {
		try {
			repository.deleteById(id);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}

	public Page<Computer> search(Pageable pageable, String name) throws UserException {
		try {
			if (Objects.isNull(name) || name.trim().isEmpty())
				return repository.findAll(pageable);
			return repository.findByNameOrCompanyName(pageable, name, name);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}

	public Computer findById(int id) throws UserException {
		try {
			return repository.findById(id);
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage());
		}
	}
}
