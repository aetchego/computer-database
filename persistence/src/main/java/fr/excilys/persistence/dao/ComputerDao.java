package fr.excilys.persistence.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import fr.excilys.model.Computer;
import fr.excilys.persistence.crud.ComputerRepository;

@Component
public class ComputerDao {

	private final ComputerRepository repository;

	public ComputerDao(ComputerRepository repository) {
		super();
		this.repository = repository;
	}

	public int count(String name) throws DataAccessException {

		if (name != null && !name.isEmpty())
			return repository.countByNameOrCompanyName(name, name);
		return ((Long) repository.count()).intValue();
	}

	public void add(Computer computer) throws DataAccessException {
		repository.save(computer);
	}

	public void delete(int id) throws DataAccessException {
		repository.deleteById(id);
	}

	public void deleteByCompanyId(int id) throws DataAccessException {
		repository.deleteByCompanyId(id);
	}

	public Page<Computer> search(Pageable pageable) throws DataAccessException {
		return repository.findAll(pageable);
	}

	public Page<Computer> findByName(Pageable pageable, String name) throws DataAccessException {
		return repository.findByNameOrCompanyName(pageable, name, name);
	}

	public Computer findById(int id) throws DataAccessException {
		return repository.findById(id);
	}
}
