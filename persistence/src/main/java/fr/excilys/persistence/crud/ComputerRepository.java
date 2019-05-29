package fr.excilys.persistence.crud;

import java.util.List;

import org.springframework.data.repository.Repository;

import fr.excilys.model.Computer;

public interface ComputerRepository extends Repository<Computer, Long> {

	List<Computer> findAll();

	void deleteComputerByCompanyId(Long CompanyId);

	<S extends Computer> S save(S entity);

	void deleteById(Integer id);
}
