package fr.excilys.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import fr.excilys.client.UserException;
import fr.excilys.config.AppConfig;
import fr.excilys.database.UTDatabase;
import fr.excilys.model.Computer;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ComputerDaoTest {

	@Autowired
	private UTDatabase database;
	@Autowired
	private ComputerDao computerDao;

	@Before
	public void loadDatabase() throws IOException, SQLException {
		database.reload();
	}

	@Test
	public void read() throws DaoException, DaoConfigException, SQLException, UserException {
		List<Computer> actual = database.readComputers(0, 150);
		List<Computer> expected = computerDao.read(0, 150);
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(expected.get(i), actual.get(i));
		}
		assertEquals(expected, actual);
		actual.clear();
		expected.clear();

		actual = database.readComputers(1, 10);
		expected = computerDao.read(1, 10);
		assertEquals(expected, actual);
		actual.clear();
		expected.clear();
	}

	@Test
	public void deleteById() throws DaoException, DaoConfigException, SQLException, UserException {
		database.deleteComputer(3);
		computerDao.delete(3);
		List<Computer> actual = database.readComputers(0, 150);
		List<Computer> expected = computerDao.read(0, 150);
		assertEquals(expected, actual);
	}

	@Test
	public void selectById() throws DaoException, DaoConfigException, SQLException, UserException {
		try {
			Optional<Computer> actual = database.selectComputerById(15);
			Optional<Computer> expected = Optional.of(computerDao.showDetails(15));
			assertEquals(expected, actual);
			computerDao.showDetails(-10);
			fail("DaoException should have been thrown");
		} catch (DaoException e) {
			assert (true);
		}
	}

	@Test
	public void count() throws DaoException, DaoConfigException, SQLException {
		int actual = database.countComputers();
		int expected = computerDao.countComputers(null);
		assertEquals(expected, actual);
	}

	@Test
	public void create() throws DaoException, DaoConfigException, SQLException, UserException {
		Computer computer = new Computer();
		computer.setName("Test");
		computer.setIntroduced(Date.valueOf("2006-1-9"));
		computer.setDiscontinued(Date.valueOf("2006-1-10"));
		database.createComputer(computer);
		computerDao.create(computer);
		assertEquals(computerDao.showDetails(computerDao.countComputers(null)),
				database.selectComputerById(database.countComputers()).get());
	}

	@Test
	public void update() throws DaoException, DaoConfigException, SQLException, UserException {
		Computer computer = new Computer();
		Computer computerCopy = database.selectComputerById(5).get();
		computer.setName("Test");
		computer.setIntroduced(Date.valueOf("2006-1-9"));
		computer.setDiscontinued(Date.valueOf("2006-1-10"));
		computer.setId(computerCopy.getId());
		computerDao.update(5, computer);
		database.updateComputer(computer, 5);
		assertEquals(computerDao.showDetails(5), database.selectComputerById(5).get());
	}
}