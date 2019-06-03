package fr.excilys.persistence.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import fr.excilys.model.Computer;

public interface ComputerRepository extends PagingAndSortingRepository<Computer, Integer> {

	@Override
	Page<Computer> findAll(Pageable pageable);

	Page<Computer> findByNameOrCompanyName(Pageable pageable, String name, String nameBis);

	Computer findById(int id);

	@Override
	<S extends Computer> S save(S entity);

	@Override
	void deleteById(Integer id);

	void deleteByCompanyId(Integer id);

	@Override
	long count();

	int countByNameOrCompanyName(String name, String Bis);
}
