package fr.excilys.services.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.excilys.model.User;
import fr.excilys.persistence.crud.UserRepository;
import fr.excilys.services.model.UserDetailsModel;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = repository.findByName(name);
		if (user == null)
			throw new UsernameNotFoundException("[ERROR] User does not exist.");
		return new UserDetailsModel(user);
	}

}
