package fr.excilys.webapp.controller.rest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.excilys.model.Computer;
import fr.excilys.persistence.crud.ComputerRepository;
import fr.excilys.webapp.controller.rest.validation.Preconditions;

@RestController
@RequestMapping("/computers")
public class ComputerRest {

	private final ComputerRepository repository;

	public ComputerRest(ComputerRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.FOUND)
	public List<Computer> findAll() {
		return Preconditions.checkFound(
				StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList()));
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public Computer findById(@PathVariable("id") Integer id) {
		return Preconditions.checkFound(repository.findById(id).get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Computer computer) {
		Preconditions.checkFound(computer);
		repository.save(computer);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") Integer id, @RequestBody Computer computer) {
		Preconditions.checkFound(computer);
		Preconditions.checkFound(id);
		computer.setId(id);
		repository.save(computer);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable("id") Integer id) {
		Preconditions.checkFound(id);
		repository.deleteById(id);
	}

}