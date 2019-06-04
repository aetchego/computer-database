package fr.excilys.persistence.crud;

import org.springframework.data.repository.Repository;

import fr.excilys.model.User;

public interface UserRepository extends Repository<User, Long> {

	public User findByName(String name);
}
