package fr.excilys.persistence.crud;

import java.util.List;

import org.springframework.data.repository.Repository;

import fr.excilys.model.Computer;

public interface ComputerRepository extends Repository<Computer, Long> {

	List<Computer> findAll();

	// void deleteComputerByCompanyId(Integer CompanyId);

	<S extends Computer> S save(S entity);

	void deleteById(Integer id);

	void deleteByCompanyId(Integer id);

	int count();

	int countByCompanyName(String name);

	int countByName(String name);
}
