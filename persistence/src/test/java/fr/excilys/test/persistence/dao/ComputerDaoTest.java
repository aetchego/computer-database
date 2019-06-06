package fr.excilys.test.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import fr.excilys.model.Computer;
import fr.excilys.persistence.crud.ComputerRepository;
import fr.excilys.test.persistence.config.PersistenceConfigTest;
import fr.excilys.test.persistence.database.DatabaseUtilitaries;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfigTest.class)
public class ComputerDaoTest {

	@Autowired
	ComputerRepository repository;
	@Autowired
	DatabaseUtilitaries database;

	@Before
	public void setUp() throws Exception {
		database.reload();
	}

	@Test
	public void findAll() {
		List<Computer> expected = StreamSupport.stream(repository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		List<Computer> result = database.findAll();
		assertEquals(expected, result);
	}

	@Test
	public void findByIdOK() {
		Computer expected = repository.findById(3);
		Computer result = database.findById(3);
		assertEquals(expected, result);
	}

	@Test
	public void findByIdNegative() {
		assertTrue(Objects.isNull(repository.findById(-3)));
	}

	@Test
	public void findByIdInexistent() {
		assertTrue(Objects.isNull(repository.findById(50000)));
	}

	@Test
	public void count() {
		long expected = repository.count();
		long result = repository.count();
		assertEquals(expected, result);
	}

	@Test
	public void deleteOK() {
		repository.deleteById(3);
		database.deleteById(3);
		assertEquals(StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList()),
				database.findAll());
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteNegative() {
		repository.deleteById(-3);
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteInexistent() {
		repository.deleteById(150000);
	}

	@Test
	public void addOK() {
		Computer computer = database.createComputer(21, "Test", null, null, 2);
		repository.save(computer);
		assertEquals(repository.findById(21), computer);
	}

	@Test
	public void addWrongId() {
		Computer computer = database.createComputer(-2, "Test", null, null, 2);
		repository.save(computer);
		assertEquals(repository.findById(-2), computer);
	}

	@Test
	public void updateLargeId() {
		Computer computer = database.createComputer(120, "Replaced", null, null, 7);
		repository.save(computer);
		assertEquals(repository.findById(120), computer);
	}

	@Test
	public void updateOK() {
		Computer computer = database.createComputer(4, "Replaced", null, null, 7);
		repository.save(computer);
		database.update(computer);
		assertEquals(repository.findById(4), database.findById(4));
	}

	@Test
	public void countByComputerOrCompanyNameOK() {
		Integer expected = repository.countByNameOrCompanyName("Apple Inc.", "Apple Inc.");
		Long result = database.countByComputerOrCompanyName("Apple Inc.");
		assertEquals(expected, result);
	}

}