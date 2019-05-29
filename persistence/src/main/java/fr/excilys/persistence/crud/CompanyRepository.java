package fr.excilys.persistence.crud;

import java.util.List;

import org.springframework.data.repository.Repository;

import fr.excilys.model.Company;

public interface CompanyRepository extends Repository<Company, Long> {
	List<Company> findAll();

	void deleteById(Long id);
}
